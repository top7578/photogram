package com.cos.photogramstart.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT i FROM Image i LEFT JOIN Fetch i.user u WHERE u.id IN :subscribeUserId")
    List<Image> mStory(List<Integer> subscribeUserId);
}
