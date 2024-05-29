package com.bbogle.yanu.domain.product.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long userId;
    private Long farmId;
    private Long productId;
    private String business_name;
    private String farm_name;
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;
    private boolean isHeart;

    public ProductResponseDto(ProductEntity productEntity, boolean isHeart) {
        this.userId = productEntity.getFarm().getUser().getId();
        this.farmId = productEntity.getFarm().getId();
        this.productId = productEntity.getId();
        this.business_name = productEntity.getFarm().getBusiness_name();
        this.farm_name = productEntity.getFarm().getFarm_name();
        this.title = productEntity.getTitle();
        this.category = productEntity.getCategory();
        this.hashtag = productEntity.getHashtag();
        this.price = productEntity.getPrice();
        this.unit = productEntity.getUnit();
        this.description = productEntity.getDescription();
        this.isHeart = isHeart;
    }
}
