package com.back.support;

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
}
