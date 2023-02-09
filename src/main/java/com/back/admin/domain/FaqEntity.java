package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "FAQ 엔티티")
public class FaqEntity extends Faq {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "게시판제목")
    public String board_title;

    @ApiModelProperty(example = "게시판유형")
    public String board_type;

    @ApiModelProperty(example = "수정자명")
    public String updated_nm;

    @ApiModelProperty(example = "사용여부")
    public String use_yn_nm;

    @ApiModelProperty(example = "조회")
    public String view;
}