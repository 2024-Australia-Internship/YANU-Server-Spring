package com.bbogle.yanu.domain.product.service;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.dto.PutProductRequestDto;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.S3Service.S3UploadService;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.TokenNotFoundException;
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
public class PutProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final S3UploadService s3UploadService;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(Long productId, List<MultipartFile> files, String removeImage, String title, String category, String hashtag, int price, String unit, String description, HttpServletRequest httpRequest) throws IOException {
        String token = tokenValidator.validateToken(httpRequest);
        Long userId = tokenProvider.getUserId(token);
        String email = userRepository.findEmailById(userId);
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

        product.updateProduct(
                title,
                category,
                hashtag,
                price,
                unit,
                description
        );

        //이미지 S3에서 삭제
        if(!removeImage.isEmpty()){
            List<String> deleteImages = List.of(removeImage.split(", "));
            for(String imageUrl : deleteImages){
                s3UploadService.deleteImage(imageUrl);
                productImageRepository.deleteAllByUrl(imageUrl);
            }
        }


        //이미지 S3 업로드
        if(files != null && !files.isEmpty()) {
            List<String> imageList = s3UploadService.uploadFilesToS3(files, email);
            for (String imageUrl : imageList) {
                ProductImageEntity newImage = ProductImageEntity.builder()
                        .url(imageUrl)
                        .product(product)
                        .build();
                productImageRepository.save(newImage);
            }
        }

        productRepository.save(product);
    }
}
