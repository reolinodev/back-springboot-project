package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "메뉴별권한 엔티티")
public class MenuAuthEntity extends MenuAuth {

    @ApiModelProperty(example = "권한명")
    public String auth_nm;

    @ApiModelProperty(example = "시스템수정자명")
    public String updated_nm;

    @ApiModelProperty(example = "수정된 코드 Row")
    public MenuAuth[] updated_rows;
}