package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindListFarmResponseDto {
    private Long userId;
    private Long farmId;
    private Double latitude;
    private Double longitude;
    private String businessName;
    private String farmName;
    private float averageStarRating;
    private boolean checkIsHeart;
    private String profile;
    private List<Review> reviews;
    private List<Product> products;

    public FindListFarmResponseDto(FarmEntity farm, float averageStarRating, List<ReviewEntity> reviews, List<ProductEntity> products, boolean checkIsHeart) {
        this.userId = farm.getUser().getId();
        this.farmId = farm.getId();
        this.latitude = farm.getGeography().getX();
        this.longitude = farm.getGeography().getY();
        this.businessName = farm.getBusinessName();
        this.farmName = farm.getFarm_name();
        this.averageStarRating = averageStarRating;
        this.checkIsHeart = checkIsHeart;
        this.profile = farm.getProfile();
        this.reviews = reviews.stream().map(Review::new).collect(Collectors.toList());
        this.products = products.stream().map(Product::new).collect(Collectors.toList());
    }
}
