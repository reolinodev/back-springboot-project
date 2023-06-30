package com.back.support;

import com.back.domain.Header;
import com.back.domain.JwtHeader;
import javax.servlet.http.HttpServletRequest;

public class ResponseUtils {

    public static Header setHeader(String message, String code,
        HttpServletRequest httpServletRequest) {
        Header header = new Header();
        header.message = message;
        header.request_url = httpServletRequest.getRequestURI();
        header.result_code = code;
        return header;
    }

    public static JwtHeader setJwtHeader(String message, String code, String accessToken,
        String refreshToken, HttpServletRequest httpServletRequest) {

        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.message = message;
        jwtHeader.request_url = httpServletRequest.getRequestURI();
        jwtHeader.result_code = code;
        jwtHeader.access_token = accessToken;
        jwtHeader.refresh_token = refreshToken;
        return jwtHeader;
    }
}
