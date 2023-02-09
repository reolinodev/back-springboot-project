package com.back.domain;

public final class Constants {

    /**
     * Resource 대상
     */
    public static final String[] resourceArray = new String[] {
//        "/page/**",
//        "/dist/**",
//        "/lib/**",
//        "/favicon.ico"
    };

    /**
     * 인터셉터 제외 대상
     */
    public static final String[] permitAllArray = new String[] {
        "/",
        "/api/certification",
        "/api/item/**/**",
        "/api/item/**/**/**",
        "/swagger",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**"
    };
}
