package com.bbogle.yanu.domain.review.controller;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.dto.CreateReviewRequestDto;
import com.bbogle.yanu.domain.review.dto.FindMyReveiwResponseDto;
import com.bbogle.yanu.domain.review.dto.FindOtherReviewResponseDto;
import com.bbogle.yanu.domain.review.service.CreateReviewService;
import com.bbogle.yanu.domain.review.service.FindMyReviewService;
import com.bbogle.yanu.domain.review.service.FindOtherReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final CreateReviewService createReviewService;
    private final FindMyReviewService findMyReviewService;
    private final FindOtherReviewService findOtherReviewService;

    @PostMapping
    public ResponseEntity<String> createReview (@RequestBody CreateReviewRequestDto request, HttpServletRequest httpRequest){
        createReviewService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 등록 성공했습니다");
    }

    @GetMapping
    public List<FindMyReveiwResponseDto> findMyReview(HttpServletRequest httpRequest){
        List<ReviewEntity> reviews = findMyReviewService.execute(httpRequest);
        return reviews.stream()
                .map(FindMyReveiwResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{user_id}")
    public List<FindOtherReviewResponseDto> findOtherReview(@PathVariable("user_id") Long userId, HttpServletRequest httpRequest){
        List<ReviewEntity> reviews = findOtherReviewService.execute(userId, httpRequest);
        return reviews.stream()
                .map(FindOtherReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}
