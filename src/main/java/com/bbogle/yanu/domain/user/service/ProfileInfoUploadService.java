package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.dto.RegisterProfileRequestDto;
import com.bbogle.yanu.domain.user.dto.RegisterRequestDto;
import com.bbogle.yanu.domain.user.facade.UserFacade;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileInfoUploadService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public void execute(RegisterProfileRequestDto request){
        String email = request.getEmail();

        userFacade.checkEmail(email);

        String nickname = request.getNickname();
        UserEntity userEntity = userRepository.findByEmail(email);
        userEntity.setNickname(nickname);
        userRepository.save(userEntity);
    }
}
