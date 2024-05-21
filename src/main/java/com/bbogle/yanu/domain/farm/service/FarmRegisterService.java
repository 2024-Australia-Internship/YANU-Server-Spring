package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.dto.RegisterFarmRequestDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.FarmDuplicateException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FarmRegisterService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public void execute(RegisterFarmRequestDto request, HttpServletRequest httpRequest){
        String token =  tokenProvider.resolveToken(httpRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }

        Long id = tokenProvider.getUserId(token);

        if(farmRepository.existsByUserId(id)){
            throw new FarmDuplicateException("farm duplicated", ErrorCode.FARM_DUPKICATION);
        }

        UserEntity user = userRepository.findUserById(id);
        request.setUserId(user);
        farmRepository.save(request.toEntity());
    }
}
