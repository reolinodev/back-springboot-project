package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "코드그룹 엔티티")
public class CodeGrpEntity extends CodeGrp {

    @ApiModelProperty(example = "순서")
    public int rnum;

}
