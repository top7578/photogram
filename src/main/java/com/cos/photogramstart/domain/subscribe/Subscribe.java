package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
                        name="subscribe_uk",
                        columnNames={"fromUserId", "toUserId"}
                )
        }
)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnoreProperties({"toSubscribe", "fromSubscribe"})
    @JoinColumn(name = "fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JsonIgnoreProperties({"toSubscribe", "fromSubscribe"})
    @JoinColumn(name = "toUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @CreationTimestamp
    private LocalDateTime createDate;
}
