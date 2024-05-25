package com.bbogle.yanu.domain.user.service;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindOtherInfoService {
    private final UserRepository userRepository;
    private final TokenValidator tokenValidator;

    public UserEntity execute(Long id, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found", ErrorCode.USER_NOTFOUND));
    }
}
