package com.back.admin.controller;

import com.back.admin.domain.MenuAuthEntity;
import com.back.admin.service.MenuAuthService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "메뉴권한관리 API")
@RequestMapping("/api/menuAuth")
@RequiredArgsConstructor
public class MenuAuthControllerAPI {

    private final MenuAuthService menuAuthService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "메뉴별 권한의 리스트를 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "menu_id", value = "메뉴식별키", required = true, dataType = "String", paramType = "path", example = "MN00000001"),
    })
    @GetMapping("/{menu_id}")
    public ResponseEntity<Map<String,Object>> getMenuAuthList(@PathVariable String menu_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        List<MenuAuthEntity> list = menuAuthService.getMenuAuthList(menu_id);

        String message = list.size()+" 개가 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    @ApiOperation(value = "메뉴별권한을 입력한다.")
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveMenuAuth(
        @ApiParam(
            value = "menu_id : 메뉴 아이디, 필수값 \n"
                +"updated_rows : 업데이트 된 로우데이터  \n"
        )
        @RequestBody MenuAuthEntity menuAuthEntity, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        menuAuthEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");

        int result = menuAuthService.saveMenuAuth(menuAuthEntity);

        String message = "메뉴별권한을 수정하였습니다";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
