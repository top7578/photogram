package com.cos.photogramstart.web.dto.image;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class SubscribeImagesDto {

    private int id;
    private String caption;
    private String postImageUrl;
    private User user;
    private List<Likes> likes;
    private boolean likeState;
    private int likeCount;

}
