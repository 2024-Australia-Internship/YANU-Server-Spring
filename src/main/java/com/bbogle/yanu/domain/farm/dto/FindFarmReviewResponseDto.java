package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindFarmReviewResponseDto {
    private Long productId;
    private String name;
    private LocalDate createAt;
    private int starrating;
    private String title;
    private String content;

    public FindFarmReviewResponseDto(ReviewEntity review){
        this.productId = review.getProduct().getId();
        this.name = review.getUser().getNickname();
        this.createAt = review.getCreateAt();
        this.starrating = review.getStarraing();
        this.title = review.getProduct().getTitle();
        this.content = review.getContent();
    }

}
