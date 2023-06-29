package com.back.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    void findShopAll() {
        //given
        String str = "서울";
        //when
        var result = shopService.findShopAll(str);
        System.out.println("result = " + result);
        //then
        Assertions.assertNotNull(result.getAddress());
        Assertions.assertNotNull(result.getImageLink());
    }

}
