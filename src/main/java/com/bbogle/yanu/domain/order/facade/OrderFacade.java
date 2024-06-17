package com.bbogle.yanu.domain.order.facade;

import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OrderFacade {
    private final ReviewRepository reviewRepository;
    public boolean checkIsReview(ReviewEntity review){
        if(review == null)
            return true;
        else
            return false;
    }
}
