package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.Product;
import com.bbogle.yanu.domain.farm.dto.Review;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchFarmResponseDto {
    private Long farmId;
    private Long userId;
    private String businessName;
    private String farmName;
    private double latitude;
    private double longitude;
    private boolean checkIsHeart;
    List<Review> reviews;
    private List<Product> products;


    public SearchFarmResponseDto(FarmEntity farm, List<ReviewEntity> reviews, List<ProductEntity> productEntities, boolean checkIsHeart){
        this.farmId = farm.getId();
        this.userId = farm.getUser().getId();
        this.businessName = farm.getBusinessName();
        this.farmName = farm.getFarm_name();
        this.latitude = farm.getGeography().getX();
        this.longitude = farm.getGeography().getY();
        this.reviews = reviews.stream().map(Review::new).collect(Collectors.toList());
        this.products = productEntities.stream().map(Product::new).collect(Collectors.toList());

        this.checkIsHeart = checkIsHeart;
    }
}
