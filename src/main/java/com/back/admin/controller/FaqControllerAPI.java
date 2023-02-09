package com.back.admin.controller;

import com.back.admin.domain.FaqEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.FaqService;
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
@Api(value = "FAQ 관리 API")
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqControllerAPI {

    private final FaqService faqService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "FAQ를 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getFaqList(
        @ApiParam(
            value = "search_str : 제목 \n"
                +"page_per : 페이지당 항목수 \n"
                +"current_page : 현재 페이지 \n"
                +"board_id : 게시판식별키 \n"
                +"use_yn : 사용여부 \n"
        )
        @RequestBody FaqEntity faqEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<FaqEntity> list = faqService.getFaqList(faqEntity);
        int listCount = faqService.getFaqCount(faqEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "FAQ를 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveFaq(
        @ApiParam(
            value = "faq_title : 제목, 필수 \n"
                +"board_id : 게시판 아이디, 필수  \n"
                +"main_text : 본문, 필수\n"
        )
        @Validated(ValidationGroups.FaqCreateGroup.class) @RequestBody FaqEntity faqEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        faqEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = faqService.saveFaq(faqEntity);

        String message = "FAQ가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="FAQ가 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "FAQ를 상세조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "faq_id", value = "FAQ식별키", required = true, dataType = "String", paramType = "path", example = "FQ00000001"),
    })
    @GetMapping("/{faq_id}")
    public ResponseEntity<Map<String,Object>> getFaqData(@PathVariable String faq_id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        FaqEntity data = faqService.getFaqData(faq_id);
        int count = 0;
        if (!"".equals(data.faq_title)) count= 1;

        String message = count+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "FAQ를 수정한다.")
    @PutMapping("/{faq_id}")
    public ResponseEntity<Map<String,Object>> updateFaq(@PathVariable String faq_id,
        @ApiParam(
            value = "faq_title : 제목, 필수 \n"
                +"main_text : 본문, 필수\n"
                +"use_yn : 사용여부, 필수\n"
        )
        @Validated(ValidationGroups.FaqUpdateGroup.class) @RequestBody FaqEntity faqEntity, HttpServletRequest httpServletRequest) throws Exception {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        faqEntity.faq_id = faq_id;
        faqEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = faqService.updateFaq(faqEntity);

        String message = "FAQ가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="FAQ가 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "FAQ 조회수를 수정한다.")
    @GetMapping("/{faq_id}/view-cnt")
    public ResponseEntity<Map<String,Object>> updateFaqViewCnt(@PathVariable String faq_id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        int result = faqService.updateFaqViewCnt(faq_id);

        String message = "FAQ 조회수가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="FAQ 조회수 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
