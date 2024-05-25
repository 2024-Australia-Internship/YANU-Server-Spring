package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
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
public class RegisterProductImageService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final S3UploadService s3UploadService;
    private final TokenProvider tokenProvider;
    private final TokenValidator tokenValidator;

    @Transactional
    public void execute(List<MultipartFile> images, Long productId, HttpServletRequest httpRequest) throws IOException {
        String token = tokenValidator.validateToken(httpRequest);

        //TODO : 이미지 등록 갯수 예외처리

        Long userId = tokenProvider.getUserId(token);
        String email = userRepository.findEmailById(userId);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));
        List<String> imageList = s3UploadService.uploadFilesToS3(images, email);

        for (String imageUrl : imageList) {
            ProductImageEntity productImage = ProductImageEntity.builder()
                    .product(product)
                    .url(imageUrl)
                    .build();
            productImageRepository.save(productImage);
        }
    }
}
