package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  //해당 파일로 시큐리티를 활성화
@Configuration      //IOC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
        //super.configure(http);

        http
                .authorizeRequests()
                    .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()                //인증이 필요한 페이지 요청이 오면
                    .loginPage("/auth/signin")  //여기로 이동
                    .defaultSuccessUrl("/");    //위 페이지에서 잘 처리하면 해당 페이지로 이동
    }
}
