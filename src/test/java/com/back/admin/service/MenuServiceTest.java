package com.back.admin.service;

import com.back.admin.domain.MenuEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void saveMenu() {
        //given
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.menu_nm = "메뉴권한정보";
        menuEntity.menu_lv = 2;
        menuEntity.auth_role = "ADMIN";
        menuEntity.prn_menu_id = "MN00000005";
        menuEntity.url = "/page/menuAuth";
        menuEntity.ord = 2;
        menuEntity.menu_type = "url";
        menuEntity.use_yn = "Y";
        menuEntity.main_yn = "N";
        menuEntity.updated_id = "US00000002";

        //when
        int result = menuService.saveMenu(menuEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void updateMenu() {
        //given
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.menu_id = "MN00000005";
        menuEntity.url = "";
        menuEntity.menu_nm = "메뉴관리";
        menuEntity.ord = 2;
        menuEntity.menu_type = "url";
        menuEntity.use_yn = "Y";
        menuEntity.main_yn = "N";
        menuEntity.updated_id = "US00000002";

        //when
        int result = menuService.updateMenu(menuEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getMenuList() {
        //given
        String authRole = "ADMIN";

        //when
        List<MenuEntity> result = menuService.getMenuTreeList(authRole);
        //then
        Assertions.assertEquals(4, result.size());
    }


    @Test
    public void getMenuData() {
        //given
        String menuId = "MN00000007";

        //when
        MenuEntity result = menuService.getMenuData(menuId);
        //then
        Assertions.assertEquals("메뉴권한정보", result.menu_nm);
    }

}