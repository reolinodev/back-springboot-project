package com.back.admin.service;

import com.back.admin.domain.UserAuthEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAuthServiceTest {
    @Autowired
    private UserAuthService userAuthService;

    @Test
    public void getAuthUserList() {
        //given
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.page_per = 10;
        userAuthEntity.current_page = 1;
        userAuthEntity.search_str = "reolino";
        userAuthEntity.auth_id = "AT00000002";

        //when
        List<UserAuthEntity> result = userAuthService.getUserAuthList(userAuthEntity);

        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAuthUserCount() {
        //given
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.search_str = "reolino";
        userAuthEntity.auth_id = "AT00000002";

        //when
        int result = userAuthService.getUserAuthCount(userAuthEntity);

        //then
        Assertions.assertEquals(2, result);
    }

    @Test
    public void getInputAuthUserList() {
        //given
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.page_per = 10;
        userAuthEntity.current_page = 1;
        userAuthEntity.auth_id = "AT00000002";
        userAuthEntity.search_str ="reolino";
        userAuthEntity.setStart_idx(userAuthEntity.getPage_per(), userAuthEntity.getCurrent_page());
        //when
        List<UserAuthEntity> result = userAuthService.getInputUserAuthList(userAuthEntity);
        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getInputAuthUserCount() {
        //given
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_id =  "AT00000002";
        userAuthEntity.search_str ="reolino";

        //when
        int result = userAuthService.getInputUserAuthCount(userAuthEntity);
        //then
        Assertions.assertEquals(2, result);
    }

    @Test
    public void saveUserAuth() {
        //given
        int result = 0;
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.user_arr = new String[]{"US00000005", "US00000006"};
        userAuthEntity.auth_id = "AT00000002";
        userAuthEntity.updated_id = "US00000007";

        //when
        String[] arr = userAuthEntity.user_arr;
        for (String j : arr) {
            userAuthEntity.user_id = j;
            result = userAuthService.saveUserAuth(userAuthEntity);
        }
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteUserAuth() throws Exception {
        //given
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.user_arr = new String[]{"US00000006"};
        userAuthEntity.auth_id = "AT00000002";
        userAuthEntity.updated_id = "US00000007";

        int result = 0;

        //when
        String[] arr = userAuthEntity.user_arr;
        for (String j : arr) {
            userAuthEntity.user_id = j;
            result = userAuthService.deleteUserAuth(userAuthEntity);
        }

        //then
        Assertions.assertEquals(1, result);
    }
}