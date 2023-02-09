package com.back.admin.service;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.Code;
import com.back.admin.domain.MenuEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void getItemCodeList() {
        //given
        String codeGrpVal = "USE_YN";
        //when
        List<Code> result = itemService.getItemCodeList(codeGrpVal);
        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getItemAuthList() {
        //given
        String use_yn = "Y";

        //when
        List<AuthEntity> result = itemService.getItemAuthList(use_yn);
        //then
        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void getItemMyAuthList() {
        //given
        String userId = "US00000002";

        //when
        List<AuthEntity> result = itemService.getItemMyAuthList(userId);
        //then
        Assertions.assertEquals(1, result.size());
    }


    @Test
    public void getPrnMenuList() {
        //given

        //when
        List<MenuEntity> result = itemService.getItemPrnMenuList();

        //then
        Assertions.assertEquals(3, result.size());
    }

}