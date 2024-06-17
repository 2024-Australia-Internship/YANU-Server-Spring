package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.farm.dto.FindUglypercentResponseDto;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.FarmNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindUglypercentService {
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public FindUglypercentResponseDto execute(Long userId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));
        boolean isFarmer = user.getIs_farmer();
        if(!isFarmer)
            throw  new FarmNotFoundException("this user is not farmer", ErrorCode.FARM_NOTFOUND);

        FarmEntity farm = farmRepository.findByUserId(userId);

        return (new FindUglypercentResponseDto(farm));
    }

}
