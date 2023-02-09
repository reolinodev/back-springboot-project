package com.back.api.service;

import com.back.domain.User;
import com.back.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        //given
        User user = new User();
        user.user_nm = "Choi2";

        //when
        var result = userService.findAll(user);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("01011113333", result.get(0).getTel_no());
    }

    @Test
    void findById() {
        //given
        String user_id = "US00000022";

        //when
        var result = userService.findById(user_id);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("Choi2", result.user_nm);
    }

    @Test
    void save() {
        //given
        User user = new User();
        user.user_nm = "Choi2";
        user.login_id = "choi2@gmail.com";
        user.user_pw = "2222";
        user.tel_no = "01011113333";

        //when
        userService.save(user);

        //then
        var result = userService.findAll(user);
        System.out.println("result = " + result);
        Assertions.assertEquals("choi2@gmail.com", result.get(0).login_id);
    }

    @Test
    void update() {
        //given
        User user = new User();
        user.user_id ="US00000022";
        user.user_nm = "Choi3";
        user.user_pw = "3333";
        user.tel_no = "01011114444";

        //when
        userService.update(user);
        var result = userService.findById("US00000022");
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals("Choi3", result.user_nm);
        Assertions.assertEquals("3333", result.user_pw);
        Assertions.assertEquals("01011114444", result.tel_no);
    }

    @Test
    void deleteById() {
        //given
        String user_id = "US00000022";

        //when
        var result = userService.deleteById(user_id);
        //then
        Assertions.assertEquals(1, result);
    }
}