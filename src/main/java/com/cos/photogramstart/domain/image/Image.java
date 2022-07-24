package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnoreProperties({"images", "toSubscribe", "fromSubscribe"})
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //이미지 좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    //댓글

    @Transient  //DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient  //DB에 칼럼이 만들어지지 않는다.
    private int likeCount;

    @CreationTimestamp
    private LocalDateTime createDate;
}
