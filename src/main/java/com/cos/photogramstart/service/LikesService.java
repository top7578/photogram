package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.likes.LikesRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Transactional
    public void 좋아요(int imageId, int principalId) {
        Image image = imageRepository.findById(imageId).get();
        User user = userRepository.findById(principalId).get();
        Likes likes = Likes.builder().
                image(image).
                user(user).
                build();
        likesRepository.save(likes);
    }

    @Transactional
    public void 좋아요취소(int imageId, int principalId) {
        Likes like = likesRepository.findLike(imageId, principalId);
        likesRepository.delete(like);
    }
}
