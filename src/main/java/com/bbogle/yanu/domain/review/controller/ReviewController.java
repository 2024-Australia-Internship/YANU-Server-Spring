package com.bbogle.yanu.domain.review.controller;

import com.bbogle.yanu.domain.review.dto.CreateReviewRequestDto;
import com.bbogle.yanu.domain.review.service.CreateReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final CreateReviewService createReviewService;

    @PostMapping
    public ResponseEntity<String> createReview (@RequestBody CreateReviewRequestDto request, HttpServletRequest httpRequest){
        createReviewService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 등록 성공했습니다");
    }
}
