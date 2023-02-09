package com.back.api.service;

import com.back.service.PlanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @Test
    void findShopAll() {
        //given
        String str = "갈비";
        //when
        var result = planService.findShopAll(str);
        System.out.println("result = " + result);
        //then
        Assertions.assertNotNull(result.getAddress());
        Assertions.assertNotNull(result.getImageLink());
    }

}