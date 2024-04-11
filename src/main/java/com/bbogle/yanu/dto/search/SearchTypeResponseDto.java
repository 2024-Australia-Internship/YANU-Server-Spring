package com.bbogle.yanu.dto.search;

import com.bbogle.yanu.controller.ProductController;
import com.bbogle.yanu.entity.ProductEntity;
import lombok.Getter;

@Getter
public class SearchTypeResponseDto {
    private Long farmId;
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;

    public SearchTypeResponseDto(ProductEntity productEntity) {
        this.farmId = productEntity.getFarm().getId();
        this.title = productEntity.getTitle();
        this.category = productEntity.getCategory();
        this.hashtag = productEntity.getHashtag();
        this.price = productEntity.getPrice();
        this.unit = productEntity.getUnit();
        this.description = productEntity.getDescription();
    }
}
