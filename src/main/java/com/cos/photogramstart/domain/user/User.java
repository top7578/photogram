package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //디비에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, unique = true)    //중복 허용 x
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; //웹 사이트
    private String bio;
    @Column(nullable = false)//자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진
    private String role;            //권한

    @OneToMany(mappedBy = "toUser")
    @JsonIgnoreProperties({"toUser", "fromUser"})
    private List<Subscribe> fromSubscribe;

    @OneToMany(mappedBy = "fromUser")
    @JsonIgnoreProperties({"toUser", "fromUser"})
    private List<Subscribe> toSubscribe;

    //나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마
    //User를 Select할 때 해당 User id로 등록된 image들을 다 가져와
    //Lazy = User를 Select할 때 해당 User id로 등록된 image들을 가져오지마- 대신 getImages() 함수의 image들이 호출될 때 가져와!
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"}) //Image 객체 안의 user 호출하지 마라. 순환참조 끊기
    private List<Image> images; //양방향 매핑

    @CreationTimestamp
    private LocalDateTime createDate;

}
