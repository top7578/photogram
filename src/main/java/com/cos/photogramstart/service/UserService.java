package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User 회원프로필(int userId) {
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> {throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");});

        return userEntity;

    }

    @Transactional
    public User 회원수정(int id, User user) {
        //1.영속화
        //1.무조건 찾았다.걱정마 get() 2.못찾았어 익섹션 발동시킬게 orElseThrow()
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> {throw new CustomValidationApiException("찾을 수 없는 id입니다");});

        //2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

       return userEntity;
    }
}
