package com.bbogle.yanu.domain.favorite.farm.dto;

import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import lombok.Getter;

@Getter
public class FindFarmHeartResponseDto {
    private Long user_id;
    private Long farm_id;

    public FindFarmHeartResponseDto(FavoriteFarmEntity favoriteFarmEntity) {
        this.user_id = favoriteFarmEntity.getUser().getId();
        this.farm_id = favoriteFarmEntity.getFarm().getId();
    }
}
