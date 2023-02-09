package com.back.api.component;

import com.back.component.NaverClient;
import com.back.domain.naver.SearchImageReq;
import com.back.domain.naver.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocal() {
        //given
        var search = new SearchLocalReq();
        search.setQuery("갈비집");
        //when
        var result = naverClient.searchLocal(search);
        System.out.println(result);
        //then
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }

    @Test
    public void searchImage() {
        //given
        var search = new SearchImageReq();
        search.setQuery("갈비집");
        //when
        var result = naverClient.searchImage(search);
        System.out.println(result);
        //then
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getLink());
    }

}