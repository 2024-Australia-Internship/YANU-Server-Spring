package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.dto.RegisterFarmRequestDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.FarmDuplicateException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FarmRegisterService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public void execute(RegisterFarmRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long id = tokenProvider.getUserId(token);

        if(farmRepository.existsByUserId(id)){
            throw new FarmDuplicateException("farm duplicated", ErrorCode.FARM_DUPKICATION);
        }

        //farms 테이블에 데이터 저장
        UserEntity user = userRepository.findUserById(id);
        farmRepository.save(request.toEntity(user));

        //users 테이블 is_farmer = true로 변경
        user.isFarmer(true);
        userRepository.save(user);
    }
}
