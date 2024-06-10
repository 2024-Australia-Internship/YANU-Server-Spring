package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.review.repository.ReviewImageRepository;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import com.bbogle.yanu.global.exception.ImageNotVaildException;
import com.bbogle.yanu.global.exception.ReviewNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateReviewImageService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final S3UploadService s3UploadService;
    private final TokenProvider tokenProvider;
    private final TokenValidator tokenValidator;

    @Transactional
    public void execute(List<MultipartFile> images, Long reviewId, HttpServletRequest httpRequest) throws IOException {
        String token = tokenValidator.validateToken(httpRequest);

        // 유효하지 않은 이미지들이 들어왔을 때 예외처리
        if(images.size() == 0 || images.size() > 5){
            throw new ImageNotVaildException("image not vaild", ErrorCode.IMAGE_NOTVALID);
        }

        Long userId = tokenProvider.getUserId(token);
        String email = userRepository.findEmailById(userId);

        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("review not found", ErrorCode.REVIEW_NOTFOUND));
        List<String> imageList = s3UploadService.uploadFilesToS3(images, email);

        for (String imageUrl : imageList) {
            ReviewImageEntity reviewImage = ReviewImageEntity.builder()
                    .review(review)
                    .url(imageUrl)
                    .build();
            reviewImageRepository.save(reviewImage);
        }
    }
}
