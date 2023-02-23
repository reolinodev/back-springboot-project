package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "게시판 엔티티")
public class BoardEntity extends Board {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "사용여부")
    private String use_yn_nm;

    @ApiModelProperty(example = "수정자명")
    public String updated_nm;

    @ApiModelProperty(example = "게시판유형")
    public String board_type_nm;

    @ApiModelProperty(example = "권한식별키 Array")
    public String[] auth_id_arr;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "게시판복합키")
    public String board_val;

}