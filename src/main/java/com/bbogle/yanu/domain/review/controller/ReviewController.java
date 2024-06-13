package com.bbogle.yanu.domain.review.controller;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.dto.*;
import com.bbogle.yanu.domain.review.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final CreateReviewService createReviewService;
    private final CreateReviewImageService createReviewImageService;
    private final FindMyReviewService findMyReviewService;
    private final FindOtherReviewService findOtherReviewService;
    private final FindProductReviewService findProductReviewService;
    private final UpdateReviewService updateReviewService;
    private final DeleteReviewService deleteReviewService;
    private final FindReviewService findReviewService;

    @PostMapping
    public ResponseEntity<Long> createReview (@RequestBody CreateReviewRequestDto request,
                                                HttpServletRequest httpRequest){
        Long reviewId = createReviewService.execute(request, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewId);
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createReviewImage(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                                    @RequestParam("review_id") Long reviewId,
                                                    HttpServletRequest httpRequest) throws IOException {
        createReviewImageService.execute(files, reviewId, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("이미지 등록 성공했습니다");
    }

    @GetMapping
    public List<FindMyReveiwResponseDto> findMyReview(HttpServletRequest httpRequest){
        return findMyReviewService.execute(httpRequest);
    }

    @GetMapping("/user/{user_id}")
    public List<FindOtherReviewResponseDto> findOtherReview(@PathVariable("user_id") Long userId,
                                                            HttpServletRequest httpRequest){
        return findOtherReviewService.execute(userId, httpRequest);
    }

    @GetMapping("/product/{product_id}")
    public List<FindProductReviewResponseDto> findProductReview(@PathVariable("product_id") Long productId,
                                                                HttpServletRequest httpRequest){
        return findProductReviewService.execute(productId, httpRequest);
    }

    @PutMapping(value = "/{product_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateReview (@PathVariable("product_id") Long productId ,
                                                @RequestParam("removeImage") String removeImage,
                                                @RequestParam("image") List<MultipartFile> images,
                                                @RequestParam("starrating") int starrating,
                                                @RequestParam("content") String content,
                                                HttpServletRequest httpRequest) throws IOException {
        updateReviewService.execute(productId, removeImage, images, starrating, content, httpRequest);
        return ResponseEntity.ok().body("리뷰 수정에 성공했습니다");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview (@RequestBody DeleteReviewRequestDto request,
                                                HttpServletRequest httpRequest){
        deleteReviewService.execute(request, httpRequest);
        return ResponseEntity.ok().body("리뷰 삭제에 성공했습니다");
    }

    @GetMapping("/{review_id}")
    public ResponseEntity<FindReviewResponseDto> findReview(@PathVariable("review_id") Long reviewId,
                                                            HttpServletRequest httpRequest){
        FindReviewResponseDto review = findReviewService.execute(reviewId, httpRequest);
        return ResponseEntity.ok().body(review);
    }
}
