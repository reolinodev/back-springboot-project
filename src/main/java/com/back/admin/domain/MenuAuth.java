package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "메뉴별권한 정보")
public class MenuAuth {

    @ApiModelProperty(example = "메뉴식별키")
    public String menu_id;
    
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
