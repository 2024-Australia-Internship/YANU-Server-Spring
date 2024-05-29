package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import lombok.Getter;

@Getter
public class FindProductHeartResponseDto {
    private Long user_id;
    private Long product_id;

    public FindProductHeartResponseDto(FavoriteProductEntity favoriteEntity) {
        this.user_id = favoriteEntity.getUser().getId();
        this.product_id = favoriteEntity.getProduct().getId();
    }
}
