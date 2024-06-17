package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.dto.LoginRequestDto;
import com.bbogle.yanu.domain.user.dto.LoginResponseDto;
import com.bbogle.yanu.domain.user.facade.UserFacade;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.EmailDuplicateException;
import com.bbogle.yanu.global.exception.PasswordNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public String execute(LoginRequestDto request){
        String email = request.getEmail();
        String password = request.getPassword();

        userFacade.checkEmail(email);

        UserEntity userEntity = userRepository.findByEmail(email);
        userFacade.checkPassword(password, userEntity.getPassword());

        String token = tokenProvider.generateToken(userEntity, Duration.ofDays(14));

        return token;
    }


}
