package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindReviewResponseDto {
    private int starrating;
    private String content;
    private LocalDate createdAt;
    private List<String> images;
    private String writerName;
    private String title;
    private String description;


    public FindReviewResponseDto(ReviewEntity reviewEntity, List<ReviewImageEntity> images){
        this.starrating = reviewEntity.getStarraing();
        this.content = reviewEntity.getContent();
        this.createdAt = reviewEntity.getCreateAt();
        this.images = images.stream()
                .filter(image -> image.getReview().getId().equals(reviewEntity.getId()))
                .map(ReviewImageEntity::getUrl)
                .collect(Collectors.toList());
        this.writerName = reviewEntity.getUser().getNickname();
        this.title = reviewEntity.getProduct().getTitle();
        this.description = reviewEntity.getProduct().getDescription();
    }
}
