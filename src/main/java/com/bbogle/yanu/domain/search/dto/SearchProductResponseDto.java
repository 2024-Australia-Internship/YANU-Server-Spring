package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class SearchProductResponseDto {
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
    private boolean isHeart;


    public SearchProductResponseDto(ProductEntity product, boolean isHeart){
        this.userId = product.getFarm().getUser().getId();
        this.farmId = product.getFarm().getId();
        this.productId = product.getId();
        this.title = product.getTitle();
        this.category = product.getCategory();
        this.hashtag = product.getHashtag();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.description = product.getDescription();
        this.business_name = product.getFarm().getBusinessName();
        this.isHeart = isHeart;

    }
}
