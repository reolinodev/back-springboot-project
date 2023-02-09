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
@ApiModel(description = "사용자권한 정보")
public class UserAuth extends Param {

    @ApiModelProperty(example = "사용자식별키")
    public String user_id;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

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
