package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "게시글 엔티티")
public class PostEntity extends Post {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "게시판제목")
    public String board_title;

    @ApiModelProperty(example = "게시판유형")
    public String board_type;

    @ApiModelProperty(example = "사용자식별키")
    public String user_id;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "수정자명")
    public String updated_nm;

    @ApiModelProperty(example = "사용여부")
    public String use_yn_nm;

    @ApiModelProperty(example = "조회")
    public String view;
}