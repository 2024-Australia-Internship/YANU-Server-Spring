package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import lombok.Getter;

@Getter
public class FindProductHeartResponseDto {
    private Long user_id;
    private Long product_id;
    private String title;
    private int price;
    private String unit;

    public FindProductHeartResponseDto(FavoriteProductEntity favoriteEntity) {
        this.user_id = favoriteEntity.getUser().getId();
        this.product_id = favoriteEntity.getProduct().getId();
        this.title = favoriteEntity.getProduct().getTitle();
        this.price = favoriteEntity.getProduct().getPrice();
        this.unit = favoriteEntity.getProduct().getUnit();
    }
}
