package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption;

    private String postImageUrl;    //사진을 전송 받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert

    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    //이미지 좋아요

    //댓글

    @CreationTimestamp
    private LocalDateTime createDate;
}
