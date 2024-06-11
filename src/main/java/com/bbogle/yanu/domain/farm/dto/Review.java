package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

@Getter
public class Review {
    private Long id;
    private String content;
    private int rating;
    private Long productId;

    public Review(ReviewEntity review){
        this.id = review.getId();
        this.content = review.getContent();
        this.rating = review.getStarraing();
        this.productId = review.getProduct().getId();
    }
}
