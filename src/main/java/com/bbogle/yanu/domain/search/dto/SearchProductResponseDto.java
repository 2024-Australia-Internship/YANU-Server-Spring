package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> productImages;


    public SearchProductResponseDto(ProductEntity product, boolean isHeart, List<ProductImageEntity> images){
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
        this.productImages = images.stream()
                .filter(image -> image.getProduct().getId().equals(product.getId()))
                .map(ProductImageEntity::getUrl)
                .collect(Collectors.toList());
    }
}
