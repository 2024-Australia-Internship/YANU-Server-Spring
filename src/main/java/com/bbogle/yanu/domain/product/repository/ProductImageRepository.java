package com.bbogle.yanu.domain.product.repository;

import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
    List<ProductImageEntity> findAllByProductId(Long productId);
    List<ProductImageEntity> findByProductIdIn(List<Long> productId);
    void deleteAllByUrl(String imgUrl);
}
