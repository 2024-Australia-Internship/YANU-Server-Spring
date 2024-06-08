package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindOtherReviewResponseDto {
    private Long userId;
    private Long productId;
    private Long farmId;
    private int starrating;
    private String content;
    private LocalDate createdAt;

    public FindOtherReviewResponseDto (ReviewEntity reviewEntity){
        this.userId = reviewEntity.getUser().getId();
        this.productId = reviewEntity.getProduct().getId();
        this.farmId = reviewEntity.getProduct().getFarm().getId();
        this.starrating = reviewEntity.getStarraing();
        this.content = reviewEntity.getContent();
        this.createdAt = reviewEntity.getCreateAt();
    }
}
