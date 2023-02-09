package com.back.domain.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataLog {

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "수정일")
    public String updated_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;
}
