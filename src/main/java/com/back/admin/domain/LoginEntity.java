package com.back.admin.domain;

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

    @ApiModelProperty(example = "접속디바이스")
    public String login_device;

    @ApiModelProperty(example = "디바이스 브라우저")
    public String device_browser;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "데이터유형")
    public String data_type;

}
