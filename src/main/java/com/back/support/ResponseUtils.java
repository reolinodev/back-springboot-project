package com.back.support;

import com.back.domain.common.JwtHeader;
import javax.servlet.http.HttpServletRequest;
import com.back.domain.common.Header;

public class ResponseUtils {

    public static Header setHeader(String message, String code,
        HttpServletRequest httpServletRequest) {
        Header header = new Header();
        header.setMessage(message);
        header.setRequestUrl(httpServletRequest.getRequestURI());
        header.setResultCode(code);
        return header;
    }

    public static JwtHeader setJwtHeader(String message, String code, String accessToken,
        String refreshToken, HttpServletRequest httpServletRequest) {

        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setMessage(message);
        jwtHeader.setRequestUrl(httpServletRequest.getRequestURI());
        jwtHeader.setResultCode(code);
        jwtHeader.setAccessToken(accessToken);
        jwtHeader.setRefreshToken(refreshToken);
        return jwtHeader;
    }
}
