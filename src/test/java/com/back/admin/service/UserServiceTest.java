package com.back.admin.service;

import com.back.admin.domain.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void checkLoginId() {
        //given
        String loginId = "reolino@gmail.com";
        //when
        var result =  userService.checkLoginId(loginId);
        System.out.println("result : " + result);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void saveUser() throws Exception {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.login_id = "AAAA@gmail.com";
        userEntity.user_nm = "A군";
        userEntity.user_pw = "1111";
        userEntity.tel_no = "01011112222";

        //when
        var result = userService.saveUser(userEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getUserList() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.page_per = 10;
        userEntity.current_page = 1;
        userEntity.search_str = "AAAA@gmail.com";
        userEntity.use_yn = "Y";
        userEntity.setStart_idx(userEntity.getPage_per(), userEntity.getCurrent_page());

        //when
        var result =  userService.getUserList(userEntity);

        System.out.println("result : " + result);

        //then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void getUserCount() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.search_str = "AAAA@gmail.com";
        userEntity.use_yn = "Y";

        //when
        var result =  userService.getUserCount(userEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getUserData() {
        //given
        String userId = "US00000007";
        //when
        var result =  userService.getUserData(userId);
        //then
        Assertions.assertEquals("reolino@gmail.com", result.login_id);
    }

    @Test
    public void updateUser() throws Exception {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.user_id =  "US00000008";
        userEntity.user_pw = "2222";
        userEntity.user_nm = "테스트";
        userEntity.tel_no = "01033334444";
        userEntity.use_yn = "N";

        //when
        var result =  userService.updateUser(userEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteUser() throws Exception {
        //given
        String userId = "US00000009";

        //when
        var result =  userService.deleteUser(userId);

        //then
        Assertions.assertEquals(1, result);
    }
}