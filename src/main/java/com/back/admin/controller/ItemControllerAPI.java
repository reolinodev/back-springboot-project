package com.back.admin.controller;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.BoardEntity;
import com.back.admin.domain.Code;
import com.back.admin.domain.MenuEntity;
import com.back.admin.service.ItemService;
import com.back.domain.Header;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "아이템 API")
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemControllerAPI {

    private final ItemService itemService;

    @ApiOperation(value = "코드그룹값에 해당하는 코드 리스트를 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_val", value = "코드그룹값", required = true, dataType = "String", paramType = "path", example = "USE_YN"),
    })
    @GetMapping("/code/{code_grp_val}")
    public ResponseEntity<Map<String,Object>> getItemCodeList(@PathVariable String code_grp_val, HttpServletRequest httpServletRequest){
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<Code> list = itemService.getItemCodeList(code_grp_val);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "사용가능한 권한의 리스트를 조회한다.")
    @GetMapping("/auth/use-yn/{use_yn}")
    public ResponseEntity <Map<String,Object>> getUseAuthList(@PathVariable String use_yn, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<AuthEntity> list = itemService.getItemAuthList(use_yn);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용가능한 권한구분별 리스트를 조회한다.")
    @PostMapping("/auth/auth-role/{auth_role}")
    public ResponseEntity <Map<String,Object>> getItemAuthRoleList(@PathVariable String auth_role, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<AuthEntity> list = itemService.getItemAuthRoleList(auth_role);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "내가 가진 권한을 가져온다.")
    @PostMapping("/auth/mine/{user_id}")
    public ResponseEntity <Map<String,Object>> getItemMyAuthList(@PathVariable String user_id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<AuthEntity> list = itemService.getItemMyAuthList(user_id);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "상위의 메뉴들을 조회한다.")
    @PostMapping("/menu/prn-menu/{auth_role}")
    public ResponseEntity<Map<String,Object>> getItemPrnMenuList(@PathVariable String auth_role, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<MenuEntity> list = itemService.getItemPrnMenuList(auth_role);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }


    @ApiOperation(value = "게시판에서 사용가능한 권한을 가져온다.")
    @PostMapping("/board/use-yn/{use_yn}")
    public ResponseEntity <Map<String,Object>> getItemUseYnBoardList(@PathVariable String use_yn, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<BoardEntity> list = itemService.getItemUseYnBoardList(use_yn);

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

}
