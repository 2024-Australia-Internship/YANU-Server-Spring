package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class CreateReviewRequestDto {
    private ProductEntity productId;
    private int starrating;
    private String content;

    public ReviewEntity toEntity(UserEntity userId, ProductEntity productId){
        return ReviewEntity.builder()
                .user(userId)
                .product(productId)
                .starraing(starrating)
                .content(content)
                .build();
    }
}
