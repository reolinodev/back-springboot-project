package com.back.admin.controller;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.AuthService;
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
@Api(value = "권한관리 API")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerAPI {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "권한을 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getAuthList(
        @ApiParam(
            value = "search_str : 권한명 / 권한코드, 널허용 \n"
                +"use_yn : 사용여부 ,널허용 \n"
                +"page_per : 페이지당 항목 수, 필수값  \n"
                +"current_page : 현재 페이지, 필수값\n"
        )
        @RequestBody AuthEntity authEntity, HttpServletRequest httpServletRequest){

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<AuthEntity> list = authService.getAuthList(authEntity);
        int listCount = authService.getAuthCount(authEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "권한을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveAuth(
        @ApiParam(
            value = "auth_nm : 아이디, 필수값, 16자 \n"
                +"auth_val : 권한 값, 필수값, 20자 (영문만)  \n"
                +"ord : 권한 순서\n"
                +"memo : 비고 \n"
        )
        @Validated(ValidationGroups.AuthCreateGroup.class)
        @RequestBody AuthEntity authEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        authEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        String message = "권한이 생성되었습니다..";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int count = authService.checkAuthCd(authEntity);

        if(count > 0){
            message ="같은 값이 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else {
            int result = authService.saveAuth(authEntity);

            if(result < 1){
                message ="권한 생성에 실패하였습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "권한 정보를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "auth_id", value = "권한식별키", required = true, dataType = "String", paramType = "path", example = "AT00000001"),
    })
    @GetMapping("/{auth_id}")
    public ResponseEntity <Map<String,Object>> getAuthData(@PathVariable String auth_id, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        AuthEntity data = authService.getAuthData(auth_id);
        int count = 0;
        if (!"".equals(data.getAuth_nm())) count= 1;

        String message = count+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "권한정보를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "auth_id", value = "권한식별키", required = true, dataType = "String", paramType = "path", example = "AT00000001"),
    })
    @PutMapping("/{auth_id}")
    public ResponseEntity<Map<String,Object>> updateAuth(
        @ApiParam(
            value = "auth_nm : 권한명, 15자 \n"
                +"auth_val : 권한코드, 20자 (영문만)  \n"
                +"auth_role : 권한구분, 20자 (영문만)  \n"
                +"ord : 권한 순서\n"
                +"memo : 비고 \n"
                +"use_yn : 사용여부 \n"
        )
        @PathVariable String auth_id,
        @Validated(ValidationGroups.AuthUpdateGroup.class) @RequestBody AuthEntity authEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        authEntity.auth_id = auth_id;
        authEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        int result = authService.updateAuth(authEntity);
        String message = "권한이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="권한 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "권한을 삭제한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "auth_id", value = "권한식별키", required = true, dataType = "String", paramType = "path", example = "USOOOOOOO1"),
    })
    @DeleteMapping("/{auth_id}")
    public ResponseEntity<Map<String,Object>> deleteAuth(@PathVariable String auth_id, HttpServletRequest httpServletRequest)
        throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        int result = authService.deleteAuth(auth_id);
        String message = "권한이 삭제 되었습니다.";
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
