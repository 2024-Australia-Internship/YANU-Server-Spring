package com.bbogle.yanu.domain.product.repository;

import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
}
