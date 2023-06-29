package com.back.service;

import com.back.component.NaverClient;
import com.back.domain.Shop;
import com.back.domain.naver.SearchImageReq;
import com.back.domain.naver.SearchLocalReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final NaverClient naverClient;

    public Shop findShopAll(String query) {

        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if (searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>\"", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            var searchImageRes = naverClient.searchImage(searchImageReq);

            if (searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                var result = new Shop();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }

        return new Shop();
    }
}
