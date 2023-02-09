package com.back.admin.controller;

import com.back.admin.domain.QnaEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.QnaService;
import com.back.domain.Header;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
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
@Api(value = "QNA관리 API")
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaControllerAPI {

    private final QnaService qnaService;
    private final JwtUtils jwtUtils;
    
    @ApiOperation(value = "QNA를 전체조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getQnaList(
        @ApiParam(
            value = "search_str : 제목, 작성자 \n"
                +"page_per : 페이지당 항목수 \n"
                +"current_page : 현재 페이지 \n"
                +"board_id : 게시판 아이디 \n"
                +"start_date : 시작일 \n"
                +"end_date : 종료일 \n"
                +"response_yn : 응답여부 \n"
                +"use_yn : 사용 여부 \n"
        )
        @RequestBody QnaEntity qnaEntity, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        List<QnaEntity> list = qnaService.getQnaList(qnaEntity);
        int listCount = qnaService.getQnaCount(qnaEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "QNA글을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveQna(
        @ApiParam(
            value = "qna_title : 제목, 필수 \n"
                +"board_id : 게시판 아이디, 필수  \n"
                +"questions : 질의, 필수\n"
                +"hidden_yn : 비밀글여부, 필수\n"
                +"qna_pw : QNA 비밀번호 \n"
        )
        @Validated(ValidationGroups.QnaCreateGroup.class) @RequestBody QnaEntity qnaEntity, HttpServletRequest httpServletRequest)
        throws Exception {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        qnaEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = qnaService.saveQna(qnaEntity);

        String message = "QNA가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="QNA 생성이 실패되었습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }


    @ApiOperation(value = "QNA글을 상세조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "qna_id", value = "QNA식별키", required = true, dataType = "String", paramType = "path", example = "QN00000001"),
    })
    @PostMapping("/{qna_id}")
    public ResponseEntity<Map<String,Object>> getQnaData(@PathVariable String qna_id,
        @ApiParam(
            value = "hidden_yn : 제목, 필수 \n"
                +"qna_pw : 비밀번호  \n"
                +"view_type : 화면유형, 필수  \n"
        )
        @RequestBody QnaEntity qnaEntity, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        qnaEntity.qna_id = qna_id;

        String message = "";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;
        QnaEntity data = new QnaEntity();

        if("Y".equals(qnaEntity.hidden_yn)&&"USER".equals(qnaEntity.view_type)){
            int result = qnaService.checkQnaPw(qnaEntity);
            if(result != 1){
                message = "비밀번호가 일치하지 않습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }else {
                data = qnaService.getQnaData(qna_id);
                int count = 0;
                if (!"".equals(data.qna_title)) count= 1;
                message = count+" 개가 조회되었습니다.";
            }
        }else {
            data = qnaService.getQnaData(qna_id);
            int count = 0;
            if (!"".equals(data.qna_title)) count= 1;
            message = count+" 개가 조회되었습니다.";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, status);
    }


    @ApiOperation(value = "QNA글을 수정한다.")
    @PutMapping("/{qna_id}")
    public ResponseEntity<Map<String,Object>> updateQna(@PathVariable String qna_id,
        @ApiParam(
            value = "qna_title : 제목 \n"
                +"questions : 질의 \n"
                +"main_text : 본문 \n"
                +"use_yn : 사용여부 \n"
                +"hidden_yn : 비밀글여부 \n"
                +"qna_pw : 비밀번호 \n"
                +"view_type : 비밀번호 \n"
        )
        @RequestBody QnaEntity qnaEntity,
        HttpServletRequest httpServletRequest) throws Exception {
        Map <String,Object> responseMap = new HashMap<>();
        qnaEntity.qna_id = qna_id;
        qnaEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = qnaService.updateQna(qnaEntity);

        String message = "QNA가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="QNA 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
