package com.back.advice;

import com.back.domain.Error;
import com.back.domain.ErrorResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class.getName());

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception e, HttpServletRequest httpServletRequest) {

        LOGGER.debug(e.getClass().getName());
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = e.getCause().getMessage();
        errorResponse.request_url = httpServletRequest.getRequestURI();
        errorResponse.result_code = "error";
        map.put("header", errorResponse);
        return new ResponseEntity<> (map, HttpStatus.INTERNAL_SERVER_ERROR);

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
            String value = "";
            if(field.getRejectedValue() != null)  value = field.getRejectedValue().toString() ;

            Error errorMessage = new Error();
            errorMessage.field = fieldName;
            errorMessage.message = message;
            errorMessage.invalid_value = value;

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.error_list = errorList;
        errorResponse.message = "Request Body를 확인해주세요.";
        errorResponse.request_url = httpServletRequest.getRequestURI();
        errorResponse.result_code = "invalid";
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("header", errorResponse);

        return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
    }
}
