package com.back.admin.controller;

import com.back.admin.domain.User;
import com.back.admin.domain.UserEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.LoginService;
import com.back.admin.service.UserService;
import com.back.domain.Header;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "사용자관리 API")
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserControllerAPI {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "사용자를 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getUserList(
        @ApiParam(
            value = "user_nm : 이름 , 널허용 \n"
                +   "login_id : 아이디 ,널허용",
            example = "{\n  user_nm:이름,\n  login_id:아이디\n}")
        @RequestBody UserEntity userEntity, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<UserEntity> list = userService.getUserList(userEntity);
        int listCount = userService.getUserCount(userEntity);

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @GetMapping("/{user_id}")
    public ResponseEntity <Map<String,Object>> getUserData(@PathVariable String user_id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        User data = userService.getUserData(user_id);

        int count = 0;
        if (!"".equals(data.login_id)) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveUser(
        @ApiParam(
            value = "user_nm : 이름, 필수값, 2~10자 \n"
                +"login_id : 이메일형식, 필수값, 이메일형식 제한 \n"
                +"user_pw : 비밀번호, 필수값, 최대 20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n"
                +"tel_no : 휴대폰, 필수값, 휴대폰번호형식 제한"
        )
        @Validated(ValidationGroups.UserCreateGroup.class) @RequestBody UserEntity userEntity, HttpServletRequest httpServletRequest)
        throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자가 생성이 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int count = userService.checkLoginId(userEntity.login_id);

        if(count > 0){
            message = "해당 아이디가 존재합니다.";
            code ="fail";
            status = HttpStatus.BAD_REQUEST;
        }else {
            userEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
            int result = userService.saveUser(userEntity);

            if(result < 1){
                message ="정상적으로 생성이 되지 않았습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }


    @ApiOperation(value = "사용자를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @PutMapping("/{user_id}")
    public ResponseEntity<Map<String,Object>> updateUser(
        @Validated(ValidationGroups.UserUpdateGroup.class) @RequestBody UserEntity userEntity,
        @PathVariable String user_id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        userEntity.user_id = user_id;
        userEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = userService.updateUser(userEntity);
        String message = "사용자 정보가 수정이 되었습니다.";
        String code = "ok";
        if(result < 1){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 삭제한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable String user_id, HttpServletRequest httpServletRequest)
        throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        int result = userService.deleteUser(user_id);
        String message = "사용자가 삭제 되었습니다.";
        String code = "ok";
        if(result < 1){
            message ="정상적으로 삭제 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자 비밀번호를 수정한다.")
    @PutMapping("/user-pw")
    public ResponseEntity<Map<String,Object>> updateUserPw(
        @RequestBody UserEntity userEntity, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        userEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        if("N".equals(userEntity.pw_init_yn)){
            userEntity.user_pw = userEntity.login_id;
        }

        int result = userService.updateUser(userEntity);
        String message = "사용자의 비밀번호가 초기화 되었습니다.";
        String code = "ok";
        if(result < 1){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 잠금상태를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @PutMapping("/pw-fail-cnt/{user_id}")
    public ResponseEntity<Map<String,Object>> updatePwFailCnt(
        @RequestBody UserEntity userEntity,
        @PathVariable String user_id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        userEntity.user_id = user_id;
        userEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        userEntity.pw_fail_init = "Y";
        userEntity.pw_fail_cnt = 0;
        int result = userService.updateUser(userEntity);
        String message = "사용자의 잠금이 해제되었습니다.";
        String code = "ok";
        if(result < 1){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
