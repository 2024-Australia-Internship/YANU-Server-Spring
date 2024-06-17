package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.OtherFarmResponseDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindOtherFarmInfoService {
    private final FarmRepository farmRepository;
    private final FavoriteFarmRepository favoriteFarmRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public OtherFarmResponseDto execute(Long user_id, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long id = tokenProvider.getUserId(token);

        FarmEntity farm = farmRepository.findByUserId(user_id);
        Long farm_id = farm.getId();

        boolean favoriteExists = favoriteFarmRepository.existsByUserIdAndFarmId(id, farm_id);
        if(favoriteExists)
            return new OtherFarmResponseDto(farm, true);
        else
            return new OtherFarmResponseDto(farm, false);

    }
}
