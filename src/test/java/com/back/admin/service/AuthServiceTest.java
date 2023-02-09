package com.back.admin.service;

import com.back.admin.domain.AuthEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    public void checkAuthCd() {
        //given

        AuthEntity authEntity = new AuthEntity();
        authEntity.auth_val = "TEST_YN";
        //when
        var result =  authService.checkAuthCd(authEntity);
        System.out.println("result : " + result);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void saveAuth() throws Exception {
        //given
        AuthEntity authEntity = new AuthEntity();
        authEntity.auth_nm = "테스트권한";
        authEntity.auth_val = "test";
        authEntity.auth_role = "admin";
        authEntity.ord = "1";
        authEntity.memo = "메모모모모모모모모";
        authEntity.created_id = "US00000002";

        //when
        var result = authService.saveAuth(authEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getAuthList() {
        //given
        AuthEntity authEntity = new AuthEntity();
        authEntity.page_per = 10;
        authEntity.current_page = 1;
        authEntity.search_str = "test";
        authEntity.use_yn = "Y";
        authEntity.setStart_idx(authEntity.getPage_per(), authEntity.getCurrent_page());

        //when
        var result =  authService.getAuthList(authEntity);

        System.out.println("result : " + result);

        //then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void getAuthCount() {
        //given
        AuthEntity authEntity = new AuthEntity();
        authEntity.search_str = "test";
        authEntity.use_yn = "Y";

        //when
        var result =  authService.getAuthCount(authEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getAuthData() {
        //given
        String authId = "AT00000001";
        //when
        var result =  authService.getAuthData(authId);
        //then
        Assertions.assertEquals("test", result.auth_val);
    }

    @Test
    public void updateAuth() throws Exception {
        //given
        AuthEntity authEntity = new AuthEntity();
        authEntity.auth_id =  "AT00000001";
        authEntity.updated_id = "US00000002";
        authEntity.auth_nm = "테스트1111";
        authEntity.ord = "1";
        authEntity.memo = "권한테스트";
        authEntity.use_yn = "N";

        //when
        var result =  authService.updateAuth(authEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteAuth() throws Exception {
        //given
        String authId = "AT00000001";

        //when
        var result =  authService.deleteAuth(authId);

        //then
        Assertions.assertEquals(1, result);
    }
}