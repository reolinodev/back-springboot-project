package com.back.admin.controller;

import com.back.admin.domain.CodeGrp;
import com.back.admin.domain.CodeGrpEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.CodeGrpService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "코드그룹관리 API")
@RequestMapping("/api/codeGrp")
@RequiredArgsConstructor
public class CodeGrpControllerAPI {

    private final CodeGrpService codeGrpService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "코드그룹 리스트를 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getCodeGrpList(
        @ApiParam(
            value = "search_str : 검색어\n"
                +   "use_yn : 사용여부\n"
                +   "page_per : 페이지당 항목수 \n"
                +   "current_page : 현재 페이지\n"
        )
        @RequestBody CodeGrpEntity codeGrpEntity, HttpServletRequest httpServletRequest){

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<CodeGrpEntity> list = codeGrpService.getCodeGrpList(codeGrpEntity);
        int listCount = codeGrpService.getCodeGrpCount(codeGrpEntity);

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드그룹을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveCodeGrp(
        @ApiParam(
            value = "code_grp_nm : 코드그룹명, 필수값, 15자 \n"
                + "code_grp_val : 코드그룹값, 필수값, 10자 \n"
        )
        @Validated(ValidationGroups.CodeGrpCreateGroup.class) @RequestBody CodeGrpEntity codeGrpEntity,
        HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        codeGrpEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        String message = "코드그룹이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int count = codeGrpService.checkCodeGrpVal(codeGrpEntity);

        if(count > 0){
            message ="중복된 코드그룹값이 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else {
            int result = codeGrpService.saveCodeGrp(codeGrpEntity);

            if(result < 1){
                message ="코드그룹 생성에 실패하였습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "코드그룹을 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_id", value = "코드그룹식별키", required = true, dataType = "String", paramType = "path", example = "CDG0000001"),
    })
    @GetMapping("/{code_grp_id}")
    public ResponseEntity <Map<String,Object>> getUserData(@PathVariable String code_grp_id, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        CodeGrp data = codeGrpService.getCodeGrpData(code_grp_id);
        int count = 0;
        if (!"".equals(data.code_grp_id)) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드그룹을 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_id", value = "코드 그룹 아이디", required = true, dataType = "String", paramType = "path", example = "1"),
    })
    @PutMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> updateCodeGrp(
        @ApiParam(
            value = "code_grp_nm : 코드그룹명, 필수값, 15자\n"
                + "use_yn : 사용여부, 필수값\n"
        )
        @PathVariable String code_grp_id,
        @Validated(ValidationGroups.CodeGrpUpdateGroup.class) @RequestBody CodeGrpEntity codeGrpEntity,
        HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        codeGrpEntity.code_grp_id = code_grp_id;
        codeGrpEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        int result = codeGrpService.updateCodeGrp(codeGrpEntity);

        String message = "코드그룹이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="코드그룹 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
