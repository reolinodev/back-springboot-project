package com.back.admin.controller;

import com.back.admin.domain.MenuEntity;
import com.back.admin.domain.common.ValidationGroups;
import com.back.admin.service.MenuAuthService;
import com.back.admin.service.MenuService;
import com.back.domain.Header;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "메뉴관리 API")
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuControllerAPI {

    private final MenuService menuService;
    private final MenuAuthService menuAuthService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "트리구조로 메뉴리스트를 가져온다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "auth_role", value = "권한구분", required = true, dataType = "String", paramType = "path", example = "ADMIN"),
    })
    @GetMapping("/tree/{auth_role}")
    public ResponseEntity<Map<String,Object>> getMenuTreeList(@PathVariable String auth_role,HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        List<MenuEntity> list = menuService.getMenuTreeList(auth_role);

        String message = list.size()+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    @ApiOperation(value = "메뉴를 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveMenu(
        @ApiParam(
            value = "menu_nm : 메뉴명, 필수값, 30자 \n"
                +"menu_lv : 레벨, 숫자형 , 필수값  \n"
                +"prn_menu_id : 상위식별키 \n"
                +"menu_url : 메뉴 도메인, 200자,영문만 \n"
                +"ord : 순서, 숫자만\n"
                +"menu_type : 메뉴의 유형 \n"
                +"use_yn : 사용여부, 필수값, (Y or N) \n"
                +"main_yn : 메인여부, 필수값, (Y or N) \n"
        )
        @Validated(ValidationGroups.MenuCreateGroup.class)
        @RequestBody MenuEntity menuEntity, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        menuEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");;

        int result = menuService.saveMenu(menuEntity);

        String message = "메뉴가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="메뉴 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "메뉴를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "menu_id", value = "메뉴 식별자", required = true, dataType = "String", paramType = "path", example = "2"),
    })
    @GetMapping("/{menu_id}")
    public ResponseEntity <Map<String,Object>> getMenuData(@PathVariable String menu_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        MenuEntity data = menuService.getMenuData(menu_id);
        int count = 0;
        if (!"".equals(data.menu_nm)) count= 1;

        String message = count+" 개가 조회되었습니다..";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "메뉴 정보를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "menu_id", value = "메뉴 식별자", required = true, dataType = "String", paramType = "path", example = "2"),
    })
    @PutMapping("/{menu_id}")
    public ResponseEntity<Map<String,Object>> updateMenu(
        @ApiParam(
            value = "menu_nm : 메뉴명, 필수값, 30자 \n"
                +"menu_url : url \n"
                +"ord : 순서 \n"
                +"menu_type : 메뉴의 타입, url or board) \n"
                +"use_yn : 사용 여부, 필수값, Y OR N \n"
                +"main_yn : 메인 여부, 필수값, Y OR N \n"
        )
        @PathVariable String menu_id,
        @Validated(ValidationGroups.MenuUpdateGroup.class)
        @RequestBody MenuEntity menuEntity,
        HttpServletRequest httpServletRequest) {

        Map <String,Object> responseMap = new HashMap<>();
        menuEntity.menu_id = menu_id;
        menuEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");;

        int result = menuService.updateMenu(menuEntity);

        String message = "메뉴가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="메뉴 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "메뉴를 삭제한다.")
    @DeleteMapping("")
    public ResponseEntity<Map<String,Object>> deleteMenu(
        @ApiParam(
            value = "menu_id : 메뉴 아이디 \n"
                +"menu_lv : 메뉴 레벨 \n"
        )
        @RequestBody MenuEntity menuEntity, HttpServletRequest httpServletRequest) {

        Map <String,Object> responseMap = new HashMap<>();
        menuAuthService.deleteMenuAuth(menuEntity.menu_id);
        int result = menuService.deleteMenu(menuEntity);

        String message = "메뉴가 삭제 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result == 0){
            message ="메뉴 삭제에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else if(result == -1){
            message ="소메뉴가 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

}
