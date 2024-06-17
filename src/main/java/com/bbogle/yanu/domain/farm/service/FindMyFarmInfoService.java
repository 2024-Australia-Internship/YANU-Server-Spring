package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.FarmNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindMyFarmInfoService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public FarmEntity execute(HttpServletRequest httpRequest) {
        String token = tokenValidator.validateToken(httpRequest);

        Long id = tokenProvider.getUserId(token);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));

        boolean isFarmer = user.getIs_farmer();
        if(!isFarmer)
            throw new FarmNotFoundException("farmer not found", ErrorCode.FARM_NOTFOUND);

        return farmRepository.findByUserId(id);
    }
}
