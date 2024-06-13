package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.review.dto.UpdateReviewRequestDto;
import com.bbogle.yanu.domain.review.repository.ReviewImageRepository;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import com.bbogle.yanu.global.config.S3Config;
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
public class UpdateReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final S3UploadService s3UploadService;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(Long productId, String removeImage, List<MultipartFile> files, int starrating, String content, HttpServletRequest httpRequest) throws IOException {
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        String email = userRepository.findEmailById(userId);

        ReviewEntity review = reviewRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ReviewNotFoundException("review not found", ErrorCode.REVIEW_NOTFOUND));

        review.updateReview(
                starrating,
                content
        );

        if (!removeImage.isEmpty()) {
            List<String> deleteImages = List.of(removeImage.split(" "));
            for (String imageUrl : deleteImages) {
                s3UploadService.deleteImage(imageUrl);
                reviewImageRepository.deleteAllByUrl(imageUrl);
            }
        }

        if (files != null && !files.isEmpty()) {
            List<String> imageList = s3UploadService.uploadFilesToS3(files, email);
            for (String imageUrl : imageList) {
                ReviewImageEntity newImage = ReviewImageEntity.builder()
                        .url(imageUrl)
                        .review(review)
                        .build();
                reviewImageRepository.save(newImage);
            }
        }

        reviewRepository.save(review);
    }
}
