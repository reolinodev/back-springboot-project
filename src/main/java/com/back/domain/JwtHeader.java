package com.back.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "JWT 헤더")
public class JwtHeader {

    public String request_url;

    public String message;

    public String result_code;

    public String access_token;

    public String refresh_token;

}
