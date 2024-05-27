package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class FindMyReveiwResponseDto {
    private Long userId;
    private Long productId;
    private int starrating;
    private String content;
    private LocalDate createdAt;

    public FindMyReveiwResponseDto (ReviewEntity reviewEntity){
        this.userId = reviewEntity.getUser().getId();
        this.productId = reviewEntity.getProduct().getId();
        this.starrating = reviewEntity.getStarraing();
        this.content = reviewEntity.getContent();
        this.createdAt = reviewEntity.getCreateAt();
    }
}
