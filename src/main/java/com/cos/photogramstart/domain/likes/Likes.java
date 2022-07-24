package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
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
@Table(     //두개를 복합적으로 uniqe
        uniqueConstraints = {
                @UniqueConstraint(
                        name="likes_uk",
                        columnNames={"imageId", "userId"}
                )
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private LocalDateTime createDate;

}
