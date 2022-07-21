package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor    //final 필드를 DI할 때 사용
@Controller     //1.IOC 등독 2.파일을 리턴하는 컨틀로러
public class AuthController {

    private final AuthService authService;

    @GetMapping("auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성검사 실패함", errorMap);
        } else {
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            return "auth/signin";
        }
    }
}
