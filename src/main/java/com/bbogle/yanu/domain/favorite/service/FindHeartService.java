package com.bbogle.yanu.domain.favorite.service;

import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.bbogle.yanu.domain.favorite.dto.FindHeartRequestDto;
import com.bbogle.yanu.domain.favorite.repository.FavoriteRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindHeartService {
    private final FavoriteRepository favoriteRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FavoriteEntity> execute(FindHeartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        String type = request.getType();
        List<FavoriteEntity> hearts = favoriteRepository.findAllByTypeAndUserId(type, userId);


        if(hearts.isEmpty())
            throw new HeartNotFoundException("heart notfound", ErrorCode.HEART_NOTFOUND);

        return hearts;

    }
}
