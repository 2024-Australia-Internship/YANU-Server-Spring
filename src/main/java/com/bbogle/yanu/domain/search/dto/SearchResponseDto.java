package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class SearchResponseDto {
    private Long userId;
    private Long farmId;
    private Long productId;
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;
    private String business_name;

    public SearchResponseDto(ProductEntity productEntity) {
        this.userId = productEntity.getFarm().getUser().getId();
        this.farmId = productEntity.getFarm().getId();
        this.productId = productEntity.getId();
        this.title = productEntity.getTitle();
        this.category = productEntity.getCategory();
        this.hashtag = productEntity.getHashtag();
        this.price = productEntity.getPrice();
        this.unit = productEntity.getUnit();
        this.description = productEntity.getDescription();
        this.business_name = productEntity.getFarm().getBusiness_name();
    }
}
