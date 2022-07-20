package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity //디비에 테이블을 생성
public class User {

    @Id @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String name;
    private String website; //웹 사이트
    private String bio;     //자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진
    private String role;            //권한

    @CreatedDate
    private LocalDateTime createDate;

}
