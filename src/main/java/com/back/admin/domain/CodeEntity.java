package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "코드 엔티티")
public class CodeEntity extends Code {

    @ApiModelProperty(example = "생성된 코드Row")
    public Code[] created_rows;

    @ApiModelProperty(example = "수정된 코드Row")
    public Code[] updated_rows;

    @ApiModelProperty(example = "삭제된 코드Row")
    public Code[] deleted_rows;
}
