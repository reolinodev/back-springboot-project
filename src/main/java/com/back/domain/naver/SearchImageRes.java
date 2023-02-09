package com.back.domain.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    private String lastBuildDate;           //검색 결과를 생성한 시간이다.
    private int total;                      //검색 결과 문서의 총 개수를 의미한다.
    private int start;                      //검색 결과 문서 중, 문서의 시작점을 의미한다.
    private int display;                    //검색된 검색 결과의 개수이다.
    private List<SearchImageItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {

        private String title;               //검색 결과 이미지의 제목을 나타낸다.
        private String link;                //검색 결과 이미지의 하이퍼텍스트 link를 나타낸다.
        private String thumbnail;           //검색 결과 이미지의 썸네일 link를 나타낸다.
        private String sizeheight;          //검색 결과 이미지의 썸네일 높이를 나타낸다.
        private String sizewidth;           //검색 결과 이미지의 너비를 나타낸다. 단위는 pixel이다.
    }
}
