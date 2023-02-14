package com.back.domain.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

    public List<Error> error_list;

    public String message;

    public String request_url;

    public String result_code;
}
