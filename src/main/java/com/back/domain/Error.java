package com.back.domain;

import lombok.Data;

@Data
public class Error {

    public String field;

    public String message;

    public String invalid_value;
}
