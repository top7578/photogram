package com.cos.photogramstart.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationApiException extends RuntimeException {

    //private String message;
    private Map<String, String> errorMap;

    public CustomValidationApiException(String message) {
        super(message);
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        //this.message = message;
        this.errorMap = errorMap;
    }
}
