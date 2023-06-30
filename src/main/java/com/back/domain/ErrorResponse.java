package com.back.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

    public String request_url;

    public String message;

    public String result_code;

    public List<Error> error_list;
}
