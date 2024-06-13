package com.bbogle.yanu.domain.favorite.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
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
    private String businessName;
    private boolean checkIsHeart;
    private List<Review> reviews;
    private List<Product> products;

    public FindFarmHeartResponseDto(FavoriteFarmEntity heart, List<ReviewEntity> reviews, List<ProductEntity> productEntities, boolean checkIsHeart){
        this.user_id = heart.getUser().getId();
        this.farm_id = heart.getFarm().getId();
        this.businessName = heart.getFarm().getBusinessName();
        this.reviews = reviews.stream().map(Review::new).collect(Collectors.toList());
        this.products = productEntities.stream().map(Product::new).collect(Collectors.toList());
        this.checkIsHeart = checkIsHeart;
    }
}
