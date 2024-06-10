package com.bbogle.yanu.domain.product.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> images;

    public ProductResponseDto(ProductEntity product, boolean isHeart, List<ProductImageEntity> images) {
        this.userId = product.getFarm().getUser().getId();
        this.farmId = product.getFarm().getId();
        this.productId = product.getId();
        this.business_name = product.getFarm().getBusinessName();
        this.farm_name = product.getFarm().getFarm_name();
        this.title = product.getTitle();
        this.category = product.getCategory();
        this.hashtag = product.getHashtag();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.description = product.getDescription();
        this.isHeart = isHeart;
        this.images = images.stream()
                .filter(image -> image.getProduct().getId().equals(product.getId()))
                .map(ProductImageEntity::getUrl)
                .collect(Collectors.toList());
    }
}
