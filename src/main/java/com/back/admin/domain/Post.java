package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.PostCreateGroup;
import com.back.admin.domain.common.ValidationGroups.PostUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "게시글 정보")
public class Post extends Param {

    @ApiModelProperty(example = "게시글식별키")
    public String post_id;

    @NotEmpty(groups = { PostCreateGroup.class}, message = "게시판식별키를 입력하세요.")
    @ApiModelProperty(example = "게시판식별키")
    public String board_id;

    @NotEmpty(groups = { PostCreateGroup.class, PostUpdateGroup.class }, message = "게시글제목을 입력하세요.")
    @ApiModelProperty(example = "게시글제목")
    public String post_title;

    @NotEmpty(groups = { PostCreateGroup.class, PostUpdateGroup.class }, message = "본문을 입력하세요.")
    @ApiModelProperty(example = "본문")
    public String main_text;

    @ApiModelProperty(example = "조회수")
    public int view_cnt;

    @NotEmpty(groups = { PostUpdateGroup.class }, message = "사용여부를 입력하세요.")
    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "수정일")
    public String updated_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;
}

