package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class FindProductHeartResponseDto {
    private Long user_id;
    private Long product_id;
    private Long farm_id;
    private String title;
    private String businessName;
    private int price;
    private String unit;
    private List<String> images;

    public FindProductHeartResponseDto(FavoriteProductEntity favoriteEntity, List<String> images) {
        this.user_id = favoriteEntity.getUser().getId();
        this.product_id = favoriteEntity.getProduct().getId();
        this.farm_id = favoriteEntity.getProduct().getFarm().getId();
        this.title = favoriteEntity.getProduct().getTitle();
        this.businessName = favoriteEntity.getProduct().getFarm().getBusinessName();
        this.price = favoriteEntity.getProduct().getPrice();
        this.unit = favoriteEntity.getProduct().getUnit();
        this.images = images;
    }
}
