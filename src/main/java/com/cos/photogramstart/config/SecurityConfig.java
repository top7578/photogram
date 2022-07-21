package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  //해당 파일로 시큐리티를 활성화
@Configuration      //IOC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
        //super.configure(http);

        http.csrf().disable();  //CSRF토큰 비활성화
                                //비정상적인 접근(서버에서 응답해준 페이지가 아닌 postman 등을 통한 요청)인지 구분

        http
                .authorizeRequests()
                    .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()                        //인증이 필요한 페이지 요청이 오면
                    .loginPage("/auth/signin")          //여기로 이동, GET
                    .loginProcessingUrl("/auth/signin") //POST -> 스프링 시큐리티가 로그인 프로세스 진행
                    .defaultSuccessUrl("/");            //위 페이지에서 잘 처리하면 해당 페이지로 이동
    }
}
