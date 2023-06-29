package com.back.controller;

import com.back.domain.Shop;
import com.back.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "shop controller Api")
public class ShopControllerApi {

    @Autowired
    ShopService shopService;

    @GetMapping("/shop")
    @ApiOperation(value = "가게를 조회한다. 네이버 검색 api")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "query", value = "검색어", dataType = "String", example = "갈비"),
    })
    public Shop findShopAll(@RequestParam String query) {
        return shopService.findShopAll(query);
    }

}
