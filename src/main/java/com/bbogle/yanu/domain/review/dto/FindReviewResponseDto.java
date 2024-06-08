package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindReviewResponseDto {
    private int starrating;
    private String content;
    private LocalDate createdAt;

    public FindReviewResponseDto(ReviewEntity reviewEntity){
        this.starrating = reviewEntity.getStarraing();
        this.content = reviewEntity.getContent();
        this.createdAt = reviewEntity.getCreateAt();
    }
}
