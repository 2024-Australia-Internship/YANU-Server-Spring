package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.Review;
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
    private double longtitude;
    private boolean checkIsHeart;
    List<Review> reviews;


    public SearchFarmResponseDto(FarmEntity farm, List<ReviewEntity> reviews, boolean checkIsHeart){
        this.farmId = farm.getId();
        this.userId = farm.getUser().getId();
        this.businessName = farm.getBusinessName();
        this.farmName = farm.getFarm_name();
        this.latitude = farm.getGeography().getX();
        this.longtitude = farm.getGeography().getY();
        this.reviews = reviews.stream().map(Review::new).collect(Collectors.toList());
        this.checkIsHeart = checkIsHeart;
    }
}
