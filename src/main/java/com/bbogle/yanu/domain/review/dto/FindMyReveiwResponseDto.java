package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindMyReveiwResponseDto {
    private Long userId;
    private Long productId;
    private Long farmId;
    private Long reviewId;
    private int starrating;
    private String content;
    private LocalDate createdAt;
    private List<String> images;
    private String writerName;
    private String title;
    private String description;


    public FindMyReveiwResponseDto (ReviewEntity reviewEntity, List<ReviewImageEntity> images){
        this.userId = reviewEntity.getUser().getId();
        this.productId = reviewEntity.getProduct().getId();
        this.farmId = reviewEntity.getProduct().getFarm().getId();
        this.reviewId = reviewEntity.getId();
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
