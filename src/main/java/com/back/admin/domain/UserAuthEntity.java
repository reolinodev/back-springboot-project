package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자권한 엔티티")
public class UserAuthEntity extends UserAuth {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "로그인 아이디")
    public String login_id;
    
    @ApiModelProperty(example = "이름")
    public String user_nm;
    
    @ApiModelProperty(example = "권한명")
    public String auth_nm;

    @ApiModelProperty(example = "사용자 집합")
    public String[] user_arr;
}
