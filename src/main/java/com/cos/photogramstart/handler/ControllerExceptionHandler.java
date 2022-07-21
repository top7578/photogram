package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.auth.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice   //모든 exception을 낚아챔
public class ControllerExceptionHandler {

//    @ExceptionHandler(CustomValidationException.class)   //RuntimeException이 발생하는 모든 exception을 해당 함수가 감지
//    public CMRespDto<?> validationException(CustomValidationException e) {
//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
//    }

    //1. 클라이언트에게 응답할 때는 Script가 좋음
    //2. Ajax통신 - CMRespDto
    //3. Android 통신 - CMRespDto
    @ExceptionHandler(CustomValidationException.class)   //RuntimeException이 발생하는 모든 exception을 해당 함수가 감지
    public String validationException(CustomValidationException e) {
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)   //RuntimeException이 발생하는 모든 exception을 해당 함수가 감지
    public ResponseEntity<?> validationException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
}
