package com.back.controller;

import com.back.domain.LoginEntity;
import com.back.service.LoginService;
import com.back.domain.common.Header;
import com.back.domain.common.JwtHeader;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginControllerAPI {

    private final LoginService loginService;

    private final JwtUtils jwtUtils;

    @ApiOperation(value = "사용자를 체크하고 인증키를 발급한다.")
    @PostMapping("/certification")
    public ResponseEntity<Map<String,Object>> login (
        @ApiParam(
            value = "login_id : 로그인 아이디, 필수 \n"
                +"user_pw : 비밀번호, 필수  \n"
        )
        @RequestBody LoginEntity loginEntity, HttpServletRequest httpServletRequest) throws Exception {

        JwtHeader jwtHeader;
        Map <String,Object> responseMap = new HashMap<>();

        HttpStatus status = HttpStatus.OK;
        String message = "";
        String code = "ok";
        String accessToken = "";
        String refreshToken = "";

        int countLoginId = loginService.checkLoginId(loginEntity);
        if(countLoginId == 0){
            message = "아이디가 존재하지 않습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        //패스워드 체크
        int countUserPw = loginService.checkUserPw(loginEntity);
        if(countUserPw == 0){
            message = "비밀번호가 일치하지 않습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;

            loginService.updatePwfailCnt(loginEntity);

            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        LoginEntity loginData = loginService.getLoginId(loginEntity);

        if(loginData.pw_fail_cnt > 5){
            message = "비밀번호 입력을 5회이상 실패하셨습니다. 관리자에게 문의 바랍니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;

            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        message = "인증키가 생성되었습니다.";
        accessToken = jwtUtils.generateToken(loginData);
        refreshToken = jwtUtils.generateRefreshToken(loginData);

        loginData.access_token = accessToken;
        loginData.refresh_token = refreshToken;
        loginService.inputToken(loginData);

        jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);

        responseMap.put("header", jwtHeader);
        return new ResponseEntity<>(responseMap, status);

    }
}
