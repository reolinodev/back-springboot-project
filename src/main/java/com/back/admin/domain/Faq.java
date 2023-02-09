package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.FaqCreateGroup;
import com.back.admin.domain.common.ValidationGroups.FaqUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "FAQ 정보")
public class Faq extends Param {

    @ApiModelProperty(example = "FAQ식별키")
    public String faq_id;

    @NotEmpty(groups = { FaqCreateGroup.class}, message = "게시판식별키를 입력하세요.")
    @ApiModelProperty(example = "게시판식별키")
    public String board_id;

    @NotEmpty(groups = { FaqCreateGroup.class, FaqUpdateGroup.class }, message = "제목을 입력하세요.")
    @ApiModelProperty(example = "제목")
    public String faq_title;

    @NotEmpty(groups = { FaqCreateGroup.class, FaqUpdateGroup.class }, message = "본문을 입력하세요.")
    @ApiModelProperty(example = "본문")
    public String main_text;

    @ApiModelProperty(example = "조회수")
    public int view_cnt;

    @NotEmpty(groups = { FaqUpdateGroup.class }, message = "사용여부를 입력하세요.")
    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "생성자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;
}

