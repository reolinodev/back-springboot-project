package com.back.admin.service;

import com.back.admin.domain.MenuAuth;
import com.back.admin.domain.MenuAuthEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuAuthServiceTest {

    @Autowired
    private MenuAuthService menuAuthService;

    @Test
    public void getMenuAuthList() {
        //given
        String menuId = "MN00000001";

        //when
        List<MenuAuthEntity> result = menuAuthService.getMenuAuthList(menuId);
        //then
        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void saveMenuAuth() {
        //given
        MenuAuthEntity menuAuthEntity = new MenuAuthEntity();
        menuAuthEntity.menu_id = "MN00000001";
        menuAuthEntity.updated_id = "US00000007";

        MenuAuth[] updatedRows = new MenuAuth[2];

        MenuAuth menuAuth1= new MenuAuth();
        menuAuth1.auth_id = "AT00000002";
        menuAuth1.use_yn = "N";
        MenuAuth menuAuth2= new MenuAuth();
        menuAuth2.auth_id = "AT00000003";
        menuAuth2.use_yn = "N";

        updatedRows[0] = menuAuth1;
        updatedRows[1] = menuAuth2;

        menuAuthEntity.updated_rows = updatedRows;

        //when
        int result = menuAuthService.saveMenuAuth(menuAuthEntity);
        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    public void deleteMenuAuth() {
        //given
        String menuId = "MN00000002";

        //when
        int result = menuAuthService.deleteMenuAuth(menuId);
        //then
        Assertions.assertEquals(1, result);
    }

}