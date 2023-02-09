package com.back.advice;

import com.back.domain.Error;
import com.back.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    /* @Valid의 유효조건 맞지 않을 경우 반환한다.*/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e,
        HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.field = fieldName;
            errorMessage.message = message;
            errorMessage.invalid_value = value;

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.error_list = errorList;
        errorResponse.message = "전송 데이터를 확인해주세요.";
        errorResponse.request_url = httpServletRequest.getRequestURI();
        errorResponse.result_code = "FAIL";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
