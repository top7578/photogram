package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {

        List<Subscribe> result = subscribeRepository.findSubscribeUser(pageUserId);

        List<SubscribeDto> resultDto = result.stream()
                .map(s -> {
                    boolean resultSubscribeState = subscribeRepository.findSubscribeState(principalId, pageUserId);

                    return SubscribeDto.builder().
                        userId(s.getToUser().getId()).
                        username(s.getToUser().getUsername()).
                        profileImageUrl(s.getToUser().getProfileImageUrl()).
                        subscribeState(resultSubscribeState ? 1: 0).
                        equalUserState(s.getToUser().getId() == principalId ? 1 : 0).
                        build();
                })
               .collect(Collectors.toList());

        return resultDto;
    }

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {

        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
