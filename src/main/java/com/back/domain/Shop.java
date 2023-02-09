package com.back.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "가게")
public class Shop {

    @ApiModelProperty(example = "인덱스")
    private Integer index;

    @ApiModelProperty(example = "음식명, 장소명")
    private String title;

    @ApiModelProperty(example = "카테고리")
    private String category;

    @ApiModelProperty(example = "주소")
    private String address;

    @ApiModelProperty(example = "도로명")
    private String roadAddress;

    @ApiModelProperty(example = "홈페이지 주소")
    private String homePageLink;

    @ApiModelProperty(example = "음식, 가게 이미지 주소")
    private String imageLink;

    @ApiModelProperty(example = "방문여부")
    private boolean isVisit;

    @ApiModelProperty(example = "방문 카운트")
    private int visitCount;

    @ApiModelProperty(example = "마지막 방문일자")
    private LocalDateTime lastVisitDate;
}
