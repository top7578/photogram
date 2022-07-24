package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


public interface LikesRepository extends JpaRepository<Likes, Integer> {

    @Query(value = "select l From Likes l Where l.image.id = :imageId AND l.user.id =:userId")
    Likes findLike(int imageId, int userId);
}
