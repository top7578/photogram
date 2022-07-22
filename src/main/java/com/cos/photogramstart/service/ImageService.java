package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

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
}
