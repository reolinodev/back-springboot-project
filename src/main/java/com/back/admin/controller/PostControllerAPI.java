package com.back.admin.controller;

import com.back.admin.domain.PostEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.PostService;
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
@Api(value = "게시글 관리 API")
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostControllerAPI {

    private final PostService postService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "게시글을 전체조회한다.")
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getPostList(
        @ApiParam(
            value = "search_str : 제목 \n"
                +"page_per : 페이지당 항목 수 \n"
                +"current_page : 현재 페이지 \n"
                +"board_id : 게시판식별키 \n"
                +"use_yn : 사용 여부 \n"
        )
        @RequestBody PostEntity postEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<PostEntity> list = postService.getPostList(postEntity);
        int listCount = postService.getPostCount(postEntity);

        String message = listCount+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> savePost(
        @ApiParam(
            value = "post_title : 제목, 필수 \n"
                +"board_id : 게시판 아이디, 필수  \n"
                +"post_text : 본문, 필수\n"
        )
        @Validated(ValidationGroups.PostCreateGroup.class) @RequestBody PostEntity postEntity, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        postEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = postService.savePost(postEntity);

        String message = "게시글이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="게시글이 실패되었습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }


    @ApiOperation(value = "게시글을 상세조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "post_id", value = "게시글식별키", required = true, dataType = "String", paramType = "path", example = "PT00000001"),
    })
    @GetMapping("/{post_id}")
    public ResponseEntity<Map<String,Object>> getPostData(@PathVariable String post_id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        PostEntity data = postService.getPostData(post_id);
        int count = 0;
        if (!"".equals(data.post_title)) count= 1;

        String message = count+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "게시글을 수정한다.")
    @PutMapping("/{post_id}")
    public ResponseEntity<Map<String,Object>> updatePost(@PathVariable String post_id,
        @Validated(ValidationGroups.PostUpdateGroup.class)@RequestBody PostEntity postEntity, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        postEntity.post_id = post_id;
        postEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        int result = postService.updatePost(postEntity);

        String message = "게시글이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="게시글 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "게시판의 입력권한이 있는지 조회한다.")
    @PostMapping("/auth")
    public ResponseEntity<Map<String,Object>> getPostAuth(
        @ApiParam(
            value = "board_id : 게시판 식별키, 필수 \n"
                +"auth_id : 권한 식별키, 필수 \n"
        )
        @RequestBody PostEntity postEntity, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        int cnt = postService.getPostAuthCnt(postEntity);

        String message = "정상적으로 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", cnt);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }
}
