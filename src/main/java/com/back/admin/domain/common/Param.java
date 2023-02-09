package com.back.admin.domain.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Param {

    @ApiModelProperty(example = "검색어")
    public String search_str;

    @ApiModelProperty(example = "페이지별 항목수")
    public int page_per = 10;

    @ApiModelProperty(example = "현재 페이지")
    public int current_page = 1;

    @ApiModelProperty(example = "시작 인덱스")
    public int start_idx;

    public void setStart_idx(int page_per, int current_page) {
        this.start_idx = page_per * (current_page - 1);
    }

    @ApiModelProperty(example = "시작일")
    public String start_date;

    @ApiModelProperty(example = "종료일")
    public String end_date;
}
