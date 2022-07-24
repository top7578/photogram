package com.cos.photogramstart.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @EntityGraph(attributePaths = "user")
    @Query(value = "SELECT i FROM Image i WHERE i.user.id IN :subscribeUserId")
    Page<Image> mStory(List<Integer> subscribeUserId, Pageable pageable);
}
