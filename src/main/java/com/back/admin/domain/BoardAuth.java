package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "게시판권한 정보")
public class BoardAuth {

    @ApiModelProperty(example = "게시판식별키")
    public String board_id;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;
}

