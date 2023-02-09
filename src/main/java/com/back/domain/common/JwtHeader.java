package com.back.domain.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "JWT 헤더")
public class JwtHeader {

    String requestUrl;

    String message;

    String resultCode;

    String accessToken;

    String refreshToken;

}
