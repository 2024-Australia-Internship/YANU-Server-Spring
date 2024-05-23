package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.dto.PasswordUpdateRequestDto;
import com.bbogle.yanu.domain.user.facade.UserFacade;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PasswordUpdateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional
    public void execute(PasswordUpdateRequestDto request){

        String email = request.getEmail();
        String password = request.getPassword();

        userFacade.checkEmail(email);

        UserEntity userEntity = userRepository.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(password);

        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
    }
}
