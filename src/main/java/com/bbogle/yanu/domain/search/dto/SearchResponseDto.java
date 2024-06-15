package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.farm.dto.Product;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
    private boolean isHeart;
    private List<String> productImages;

    public SearchResponseDto(ProductEntity productEntity, boolean isHeart, List<ProductImageEntity> images) {
        this.userId = productEntity.getFarm().getUser().getId();
        this.farmId = productEntity.getFarm().getId();
        this.productId = productEntity.getId();
        this.title = productEntity.getTitle();
        this.category = productEntity.getCategory();
        this.hashtag = productEntity.getHashtag();
        this.price = productEntity.getPrice();
        this.unit = productEntity.getUnit();
        this.description = productEntity.getDescription();
        this.business_name = productEntity.getFarm().getBusinessName();
        this.isHeart = isHeart;
        this.productImages = images.stream()
                .filter(image -> image.getProduct().getId().equals(productEntity.getId()))
                .map(ProductImageEntity::getUrl)
                .collect(Collectors.toList());
    }
}
