package com.back.admin.controller;

import com.back.admin.domain.UserAuthEntity;
import com.back.admin.service.UserAuthService;
import com.back.domain.Header;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "사용자권한관리 API")
@RequestMapping("/api/userAuth")
@RequiredArgsConstructor
public class UserAuthControllerAPI {

    private final UserAuthService userAuthService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "사용자의 권한을 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getUserAuthList(
        @ApiParam(
            value = "auth_id : 권한 아이디 , 널허용 \n"
                +   "search_str : 검색어 ,널허용\n"
                +   "page_per : 페이지당 항목수 ,필수\n"
                +   "current_page : 현재 페이지, 필수\n")
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest){

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<UserAuthEntity> list = userAuthService.getUserAuthList(userAuthEntity);
        int listCount = userAuthService.getUserAuthCount(userAuthEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "권한을 입력할 대상을 전체 조회한다.")
    @PostMapping("/input-user")
    public ResponseEntity<Map<String,Object>> getInputUserAuthList(
        @ApiParam(
            value = "auth_id : 권한 아이디 , 필수 \n"
                +   "search_str : 검색어 ,널허용\n"
                +   "page_per : 페이지당 항목수\n"
                +   "current_page : 현재 페이지\n")
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest){

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<UserAuthEntity> list = userAuthService.getInputUserAuthList(userAuthEntity);
        int listCount = userAuthService.getInputUserAuthCount(userAuthEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 권한을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveUserAuth(
        @ApiParam(
            value = "auth_id : 권한 아이디, 필수값 \n"
                +"user_arr : 사용자 아이디 array, 필수값 \n"
        )
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        userAuthEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        String message = "사용자의 권한이 부여되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int result = userAuthService.saveUserAuth(userAuthEntity);

        if(result < 0){
            message ="사용자 권한 부여에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }


    @ApiOperation(value = "사용자의 권한을 삭제한다.")
    @DeleteMapping("")
    public ResponseEntity<Map<String,Object>> deleteUserAuth(
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest) throws Exception {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        userAuthEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");;

        int result = userAuthService.deleteUserAuth(userAuthEntity);

        String message = "사용자의 권한이 삭제되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="사용자 권한 삭제에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
