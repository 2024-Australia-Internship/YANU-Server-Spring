package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindProductReviewResponseDto {
    private Long userId;
    private Long productId;
    private Long farmId;
    private int starrating;
    private String content;
    private LocalDate createdAt;
    private List<String> images;
    private String writerName;
    private String title;
    private String description;
    private String profile;



    public FindProductReviewResponseDto (ReviewEntity reviewEntity){
        this.userId = reviewEntity.getUser().getId();
        this.productId = reviewEntity.getProduct().getId();
        this.farmId = reviewEntity.getProduct().getFarm().getId();
        this.starrating = reviewEntity.getStarraing();
        this.content = reviewEntity.getContent();
        this.createdAt = reviewEntity.getCreateAt();
        this.profile = reviewEntity.getUser().getProflie_image();
        this.writerName = reviewEntity.getUser().getNickname();
        this.title = reviewEntity.getProduct().getTitle();
        this.description = reviewEntity.getProduct().getDescription();
    }
}
