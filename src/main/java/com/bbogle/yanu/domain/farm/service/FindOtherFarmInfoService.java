package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindOtherFarmInfoService {
    private final FarmRepository farmRepository;
    private final TokenValidator tokenValidator;

    public FarmEntity execute(Long id, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        return farmRepository.findByUserId(id);
    }
}
