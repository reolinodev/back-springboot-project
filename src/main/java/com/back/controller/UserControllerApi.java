package com.back.controller;

import com.back.domain.User;
import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.service.UserService;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "user controller Api")
@RequestMapping("/api/user")
public class UserControllerApi {

    @Autowired
    UserService userService;

    @ApiOperation(value = "사용자를 전체 조회한다.")
    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> getUserList(
        @ApiParam(
            value = "user_nm : 이름 , 널허용 \n"
                +   "login_id : 이메일 ,널허용",
            example = "{\n  user_nm:이름,\n  login_id:이메일\n}")
        @RequestBody User user, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<User> list = userService.findAll(user);
        int listCount = list.size();

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @GetMapping("/{user_id}")
    public ResponseEntity <Map<String,Object>> getUserData(@PathVariable String user_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        User data = userService.findById(user_id);

        String message = "1건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 입력한다.")
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> inputUser(
        @ApiParam(
            value = "user_nm : 이름, 필수값, 2~10자 \n"
                +"login_id : 이메일형식, 필수값, 이메일형식 제한 \n"
                +"user_pw : 비밀번호, 필수값, 최대 20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n"
                +"tel_no : 휴대폰, 필수값, 휴대폰번호형식 제한"
        )
        @Validated(ValidationGroups.UserCreateGroup.class) @RequestBody User user, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        int result = userService.save(user);
        String message = "사용자가 생성이 되었습니다.";
        String code = "ok";
        if(result < 1){
            message ="정상적으로 생성이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }


    @ApiOperation(value = "사용자를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user_id", value = "사용자식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @PutMapping("/{user_id}")
    public ResponseEntity<Map<String,Object>> updateUser(
        @Validated(ValidationGroups.UserUpdateGroup.class) @RequestBody User user,
        @PathVariable String user_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        user.user_id = user_id;
        int result = userService.update(user);
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
        @ApiImplicitParam(name = "user_id", value = "사용자 고유키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable String user_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        int result = userService.deleteById(user_id);
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
}
