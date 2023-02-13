package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자 엔티티")
public class UserEntity extends User {

    @ApiModelProperty(example = "순서")
    public int rnum;

    @ApiModelProperty(example = "사용여부")
    public String use_yn_nm;

    @ApiModelProperty(example = "비밀번호실패초기화")
    public String pw_fail_init;
}
