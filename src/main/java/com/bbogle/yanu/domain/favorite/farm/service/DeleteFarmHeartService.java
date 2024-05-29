package com.bbogle.yanu.domain.favorite.farm.service;

import com.bbogle.yanu.domain.favorite.farm.dto.DeleteFarmHeartRequestDto;
import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
import com.bbogle.yanu.domain.favorite.product.dto.DeleteHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFarmHeartService {
    private final FavoriteFarmRepository favoriteFarmRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(DeleteFarmHeartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long farmId = request.getFarmId().getId();

        boolean exists = favoriteFarmRepository.existsByUserIdAndFarmId(userId, farmId);
        if(!exists){
            throw new HeartNotFoundException("heart not found", ErrorCode.HEART_NOTFOUND);
        }

        favoriteFarmRepository.deleteByUserIdAndFarmId(userId, farmId);
    }
}
