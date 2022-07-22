package com.cos.photogramstart.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {

    //private String message;
    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        //this.message = message;
        this.errorMap = errorMap;
    }

    public CustomValidationException(String message) {
        super(message);
    }
}
