package com.back.admin.controller;

import com.back.admin.domain.BoardEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.BoardService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "게시판관리 API")
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardControllerAPI {

    private final BoardService boardService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "게시판을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveBoard(
        @ApiParam(
            value = "board_title : 제목, 필수 \n"
                +"board_type : 게시판유형, 필수  \n"
                +"memo : 메모, 15자  \n"
                +"auth_id_arr : 권한식별키, 15자  \n"
        )
        @Validated(ValidationGroups.BoardCreateGroup.class) @RequestBody BoardEntity boardEntity, HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        boardEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        int result = boardService.saveBoard(boardEntity);

        String message = "게시판이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="게시판 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "게시판을 전체 조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getBoardList(
        @ApiParam(
            value = "search_str : 이름 / 아이디 \n"
                +"page_per : 페이지당 항목 수  \n"
                +"current_page : 현재 페이지 \n"
                +"board_type : 게시판유형 \n"
                +"use_yn : 사용 여부 \n"
        )
        @RequestBody BoardEntity boardEntity, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<BoardEntity> list = boardService.getBoardList(boardEntity);
        int listCount = boardService.getBoardCount(boardEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "게시판을 상세조회한다.")
    @GetMapping("/{board_id}")
    public ResponseEntity <Map<String,Object>> getBoardData(@PathVariable String board_id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        BoardEntity data = boardService.getBoardData(board_id);
        int count = 0;
        if (!"".equals(data.board_title)) count= 1;

        List<BoardEntity> boardAuthList = boardService.getBoardAuthList(board_id);

        String message = count+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);
        responseMap.put("boardAuthList", boardAuthList);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판을 수정한다.")
    @PutMapping("/{board_id}")
    public ResponseEntity<Map<String,Object>> updateBoard(@PathVariable String board_id,
        @Validated(ValidationGroups.BoardUpdateGroup.class) @RequestBody BoardEntity boardEntity, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        boardEntity.board_id = board_id;
        boardEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        int result = boardService.updateBoard(boardEntity);

        String message = "게시판이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="게시판 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
