package com.back.filter;

import com.back.admin.domain.LoginEntity;
import com.back.admin.service.LoginService;
import com.back.support.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final LoginService loginService;

    @Value("${token.long}")
    String tokenLong;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {


        String token = jwtUtils.resolveToken((HttpServletRequest) request);

        if(token == null){
            //swagger에서 값을 넣는 경우 추가
            token = request.getHeader("jwt");
        }

        if(token != null){

            LoginEntity params = new LoginEntity();
            params.access_token = token;

            if (jwtUtils.validateToken(token)) {
                if("Y".equals(tokenLong)){
                    Authentication authentication = jwtUtils.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    LoginEntity loginEntity = loginService.getTokenInfo(params);

                    if(loginEntity != null){
                        Authentication authentication = jwtUtils.getAuthentication(token);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else{
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, Object> resBody = new HashMap<>();
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        resBody.put("result_code", HttpStatus.FORBIDDEN.value());
                        resBody.put("message", "인증토큰이 유효하지 않습니다.");
                        mapper.writeValue(response.getWriter(), resBody);
                    }
                }
            }
            else if(!jwtUtils.validateToken(token)) {

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> resBody = new HashMap<>();

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                if("Y".equals(tokenLong)){
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    resBody.put("result_code", HttpStatus.FORBIDDEN.value());
                    resBody.put("message", "인증토큰이 유효하지 않습니다.");
                }else{
                    LoginEntity loginEntity = loginService.getTokenInfo(params);
                    String refreshToken = "";
                    if(loginEntity != null){
                        refreshToken = loginEntity.refresh_token;
                    }

                    if(!"".equals(refreshToken)){
                        if (jwtUtils.validateToken(refreshToken)) {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());

                            LoginEntity loginData = loginService.getLoginId(loginEntity);
                            String accessToken = jwtUtils.generateToken(loginData);
                            loginData.access_token = accessToken;
                            loginService.saveToken(loginData);

                            resBody.put("result_code", HttpStatus.UNAUTHORIZED.value());
                            resBody.put("message", "새 인증토큰을 발급했습니다.");
                            resBody.put("new_token", accessToken);
                            resBody.put("new_expired_token_time", jwtUtils.getValidTime(accessToken));
                        }else {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            resBody.put("result_code", HttpStatus.FORBIDDEN.value());
                            resBody.put("message", "인증토큰이 유효하지 않습니다.");
                        }

                    }else{
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        resBody.put("result_code", HttpStatus.FORBIDDEN.value());
                        resBody.put("message", "인증토큰이 유효하지 않습니다.");
                    }
                }

                mapper.writeValue(response.getWriter(), resBody);
            }
        }

        filterChain.doFilter(request , response);
    }
}
