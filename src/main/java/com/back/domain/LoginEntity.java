package com.back.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "로그인")
public class LoginEntity extends User {

    @ApiModelProperty(example = "억세스토큰")
    public String access_token;

    @ApiModelProperty(example = "리프레시토큰")
    public String refresh_token;
}
