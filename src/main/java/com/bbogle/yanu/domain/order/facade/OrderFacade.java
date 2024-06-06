package com.bbogle.yanu.domain.order.facade;

import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OrderFacade {
    private final ReviewRepository reviewRepository;
    public boolean checkIsReview(ProductEntity product, Long userId){
        Long productId = product.getId();

        return reviewRepository.existsByUserIdAndProductId(userId, productId);
    }
}
