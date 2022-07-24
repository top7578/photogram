package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import com.cos.photogramstart.web.dto.image.SubscribeImagesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    private boolean likeState = false;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        MultipartFile file = imageUploadDto.getFile();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" +file.getOriginalFilename();

        File saveFile = new File(uploadFolder, imageFileName);

        //통신, I/O -> 예외가 발생할 수 있다. 런타임 시에만 발생한다.
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);

    }

    @Transactional(readOnly = true)
    public Page<SubscribeImagesDto> 이미지스토리(int id, Pageable pageable) {

        User user = userRepository.findUser(id);

        List<Integer> subscribeUserIdLst = new ArrayList<>();

        List<Subscribe> ts = user.getToSubscribe();
        ts.stream().forEach(s -> {
            subscribeUserIdLst.add(s.getToUser().getId());
        });

        Page<Image> imageList = imageRepository.mStory(subscribeUserIdLst, pageable);

        //images에 좋아요 상태 담기
        Page<SubscribeImagesDto> resultDto = imageList
                .map((image) -> {
                    likeState = false;
                    image.getLikes().forEach((like) -> {
                        if (like.getUser().getId() == id) {
                            likeState = true;
                        }
                    });

                    return SubscribeImagesDto.builder()
                            .id(image.getId())
                            .caption(image.getCaption())
                            .postImageUrl(image.getPostImageUrl())
                            .user(image.getUser())
                            .likes(image.getLikes())
                            .likeState(likeState)
                            .likeCount(image.getLikes().size())
                            .build();
                });
        return resultDto;
    }
}
