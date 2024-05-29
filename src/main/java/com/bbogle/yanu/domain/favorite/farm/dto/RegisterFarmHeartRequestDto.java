package com.bbogle.yanu.domain.favorite.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class RegisterFarmHeartRequestDto {
    private FarmEntity farmId;

    public FavoriteFarmEntity toEntity(UserEntity userId){
        return FavoriteFarmEntity.builder()
                .user(userId)
                .farm(farmId)
                .build();
    }
}
