package com.back.admin.controller;

import com.back.admin.domain.CodeEntity;
import com.back.admin.service.CodeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "코드관리 API")
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeControllerAPI {

    private final CodeService codeService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "코드리스트를 전체 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_id", value = "코드그룹식별키", required = true, dataType = "String", paramType = "path", example = "CDG0000001"),
    })
    @GetMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> getCodeList(@PathVariable String code_grp_id, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<CodeEntity> list = codeService.getCodeList(code_grp_id);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드를 저장한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_id", value = "코드그룹식별키", required = true, dataType = "String", paramType = "path", example = "CDG0000001"),
    })
    @PutMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> saveCode(
        @ApiParam(
            value = "created_rows : 생성할 코드 데이터 \n"
                + "updated_rows : 수정할 코드 데이터 \n"
                + "deleted_rows : 삭제할 코드 데이터 \n"
        )
        @RequestBody CodeEntity codeEntity, @PathVariable String code_grp_id,
        HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        codeEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        codeEntity.code_grp_id = code_grp_id;

        String message = "코드가 저장되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        int result = codeService.saveCode(codeEntity);
        if(result == 0){
            message ="코드가 정상적으로 저장되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
