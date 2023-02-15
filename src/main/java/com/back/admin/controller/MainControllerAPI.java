package com.back.admin.controller;

import com.back.admin.domain.LoginEntity;
import com.back.admin.domain.MenuEntity;
import com.back.admin.service.MenuService;
import com.back.domain.Header;
import com.back.support.JsonUtils;
import com.back.support.JwtUtils;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "메인화면 API")
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainControllerAPI {

    private final MenuService menuService;
    private final JwtUtils jwtUtils;

    @Value("${data.type}")
    private String dataType;

    @ApiOperation(value = "인증된 사용자 정보를 가져온다.")
    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> getUserData(HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message ="정상적으로 조회되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.user_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        loginEntity.auth_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"auth_id");
        loginEntity.login_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"login_id");
        loginEntity.user_nm = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_nm");
        loginEntity.data_type = dataType;

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", loginEntity);

        return new ResponseEntity<> (responseMap,  status);
    }

    @ApiOperation(value = "메뉴목록을 조회한다.")
    @PostMapping("/nav")
    public ResponseEntity<Map<String,Object>> getNavList(
        @RequestBody MenuEntity menuEntity, HttpServletRequest httpServletRequest)
        throws IOException, ParseException {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();

        String message ="정상적으로 조회되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if("json".equals(dataType)) {
            data = JsonUtils.getJsonMenu();
        }
        else {
            MenuEntity menuUrl = menuService.getUrlData(menuEntity);
            menuEntity.menu_lv = 1;
            List<MenuEntity> menuLv1List = menuService.getMenuList(menuEntity);
            menuEntity.menu_lv = 2;
            List<MenuEntity> menuLv2List = menuService.getMenuList(menuEntity);
            data.put("menuUrl", menuUrl);
            data.put("menuLv1List", menuLv1List);
            data.put("menuLv2List", menuLv2List);
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap,  status);
    }
}
