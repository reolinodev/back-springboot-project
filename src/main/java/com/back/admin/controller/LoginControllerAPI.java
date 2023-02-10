package com.back.admin.controller;

import com.back.admin.domain.LoginEntity;
import com.back.admin.service.LoginService;
import com.back.domain.Header;
import com.back.domain.JwtHeader;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import io.swagger.models.Response;
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
@Api(value = "로그인 API")
@RequestMapping("/api")
public class LoginControllerAPI {

    private final LoginService loginService;

    private final JwtUtils jwtUtils;

    @ApiOperation(value = "사용자를 체크하고 인증키를 발급한다.")
    @PostMapping("/certification")
    public ResponseEntity<Map<String,Object>> certification (
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
        loginService.saveToken(loginData);

        jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);

        responseMap.put("header", jwtHeader);
        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "사용자를 정보를 체크하고 이력을 관리한다.")
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login (
        @ApiParam(
            value = "login_id : 로그인 아이디, 필수 \n"
                +"user_pw : 비밀번호, 필수  \n"
                +"login_device : 사용자 디바이스   \n"
                +"device_browser : 사용자 브라우저  \n"
        )
        @RequestBody LoginEntity loginEntity, HttpServletRequest httpServletRequest) throws Exception {

        Map <String,Object> responseMap = new HashMap<>();
        Header header = new Header();

        String message = "";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        LoginEntity loginInfo = loginService.getLoginId(loginEntity);

        int pwFailCnt = loginInfo.pw_fail_cnt;
        String pwInitYn = loginInfo.pw_init_yn;

        //5회이상 실패했을 경우
        if(pwFailCnt > 5){
            message = "비밀번호를 5회이상 실패하였습니다. 관리자에게 문의하세요.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }
        //초기화를 안했을때
        else if("N".equals(pwInitYn)) {
            message = "비밀번호 초기화가 필요합니다. 비밀번호 변경 화면으로 이동합니다.";
            code = "pwchange";
        }
        else{
            loginEntity.user_id = loginInfo.user_id;
            loginService.saveLoginHistory(loginEntity);
            int result = loginService.updateLastLoginDt(loginInfo);

            if(result < 1){
                message = "로그인에 실패하였습니다..";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }else{
                message = "메인화면으로 이동합니다.";
            }
        }

        header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", loginInfo);

        return new ResponseEntity<>(responseMap, status);
    }

}
