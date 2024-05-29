package com.bbogle.yanu.domain.product.facade;

import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ProductFacade {
    private final FavoriteProductRepository favoriteProductRepository;
    public boolean checkIsHeart(ProductEntity product, Long userId){
        Long productId = product.getId();

        return favoriteProductRepository.existsByUserIdAndProductId(userId, productId);
    }
}
