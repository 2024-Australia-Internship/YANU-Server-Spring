package com.bbogle.yanu.domain.product.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class ProductFarmResponseDto {
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;

    public ProductFarmResponseDto(ProductEntity productEntity) {
        this.title = productEntity.getTitle();
        this.category = productEntity.getCategory();
        this.hashtag = productEntity.getHashtag();
        this.price = productEntity.getPrice();
        this.unit = productEntity.getUnit();
        this.description = productEntity.getDescription();
    }
}
