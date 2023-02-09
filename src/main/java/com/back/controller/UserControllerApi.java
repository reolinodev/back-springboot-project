package com.back.controller;

import com.back.domain.User;
import com.back.service.UserService;
import com.back.domain.common.Header;
import com.back.support.ResponseUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "user controller Api")
@RequestMapping("/api")
public class UserControllerApi {

    @Autowired
    UserService userService;

    @ApiOperation(value = "사용자를 전체 조회한다.")
    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> findAll(
        @ApiParam(
            value = "name : 이름 , 널허용 \n"
                + "email : 이메일 ,널허용",
            example = "{\n  name:이름,\n  email:이메일\n}")
        @RequestBody User user, HttpServletRequest httpServletRequest) {
        Map<String, Object> responseMap = new HashMap<>();

        List<User> list = userService.findAll(user);
        int listCount = list.size();

        String message = listCount + "건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id,
        HttpServletRequest httpServletRequest) {
        Map<String, Object> responseMap = new HashMap<>();
        User data = userService.findById(id);

        String message = "1건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 입력한다.")
    @PutMapping("/user")
    public ResponseEntity<Map<String, Object>> save(
        @ApiParam(
            value = "name : 이름, 필수값, 2~10자 \n"
                + "email : 이메일, 필수값, 이메일형식 제한 \n"
                + "birth : 생년월일, 널허용, 8자리(19000101) 형식 \n"
                + "password : 비밀번호, 필수값, 최대 20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n"
                + "phone : 휴대폰, 필수값, 휴대폰번호형식 제한"
        )
        @Valid @RequestBody User user, HttpServletRequest httpServletRequest) {
        Map<String, Object> responseMap = new HashMap<>();

        int result = userService.save(user);
        String message = "사용자가 생성이 되었습니다.";
        String code = "ok";
        if (result < 1) {
            message = "정상적으로 생성이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }


    @ApiOperation(value = "사용자를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
        @Valid @RequestBody User user,
        @PathVariable Integer id, HttpServletRequest httpServletRequest) {

        Map<String, Object> responseMap = new HashMap<>();

        user.setId(id);
        int result = userService.update(user);
        String message = "사용자 정보가 수정이 되었습니다.";
        String code = "ok";
        if (result < 1) {
            message = "정상적으로 수정이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 삭제한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id,
        HttpServletRequest httpServletRequest) {
        Map<String, Object> responseMap = new HashMap<>();

        int result = userService.deleteById(id);
        String message = "사용자가 삭제 되었습니다.";
        String code = "ok";
        if (result < 1) {
            message = "정상적으로 삭제 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
