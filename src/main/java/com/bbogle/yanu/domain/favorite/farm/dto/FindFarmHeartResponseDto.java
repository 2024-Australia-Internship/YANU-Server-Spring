package com.bbogle.yanu.domain.favorite.farm.dto;

import com.bbogle.yanu.domain.farm.dto.Product;
import com.bbogle.yanu.domain.farm.dto.Review;
import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindFarmHeartResponseDto {
    private Long user_id;
    private Long farm_id;
    private String farmName;
    private String businessName;
    private boolean checkIsHeart;
    private String farmProfile;
    private List<Review> reviews;
    private List<Product> products;

    public FindFarmHeartResponseDto(FavoriteFarmEntity heart, List<ReviewEntity> reviews, List<ProductEntity> productIds, boolean checkIsHeart) {
        this.user_id = heart.getFarm().getUser().getId();
        this.farm_id = heart.getFarm().getId();
        this.farmName = heart.getFarm().getFarm_name();
        this.businessName = heart.getFarm().getBusinessName();
        this.checkIsHeart = checkIsHeart;
        this.farmProfile = heart.getFarm().getProfile();
        this.reviews = reviews.stream().map(Review::new).collect(Collectors.toList());
        this.products = productIds.stream().map(Product::new).collect(Collectors.toList());
    }
}
