package com.back.admin.service;

import com.back.admin.domain.LoginEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void checkLoginId() {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";

        //when
        var result = loginService.checkLoginId(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void checkUserPw() throws Exception {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";
        loginEntity.user_pw = "1111";

        //when
        var result = loginService.checkUserPw(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void getLoginId()  {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";

        //when
        var result = loginService.getLoginId(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("reolino", result.user_nm);
    }


    @Test
    void updateLastLoginDt()  {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";

        //when
        var result = loginService.updateLastLoginDt(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void updatePwfailCnt()  {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";

        //when
        loginService.updatePwfailCnt(loginEntity);

        var result = loginService.getLoginId(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result.pw_fail_cnt);
    }

    @Test
    void inputToken()  {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";
        loginEntity.access_token = "cccc";
        loginEntity.refresh_token = "dddd";

        //when
        var result =  loginService.saveToken(loginEntity);

        //then
        Assertions.assertEquals(2, result);
    }


    @Test
    void getTokenInfo()  {
        //given
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.login_id = "reolino@gmail.com";
        loginEntity.access_token = "aaaa";

        //when
        var result = loginService.getTokenInfo(loginEntity);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("bbbbb", result.refresh_token);
    }

}