package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindFarmReviewResponseDto {
    private Long productId;
    private String name;
    private LocalDate createAt;
    private int starrating;
    private String title;
    private String content;
    private List<String> reviewImages;

    public FindFarmReviewResponseDto(ReviewEntity review, List<ReviewImageEntity> images){
        this.productId = review.getProduct().getId();
        this.name = review.getUser().getNickname();
        this.createAt = review.getCreateAt();
        this.starrating = review.getStarraing();
        this.title = review.getProduct().getTitle();
        this.content = review.getContent();
        this.reviewImages = images.stream()
                .map(ReviewImageEntity::getUrl)
                .collect(Collectors.toList());
    }

}
