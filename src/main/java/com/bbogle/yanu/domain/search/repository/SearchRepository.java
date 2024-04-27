package com.bbogle.yanu.domain.search.repository;


import com.bbogle.yanu.domain.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByTitleContaining(String keyword);
    List<ProductEntity> findAllByTitleContainingAndCategory(String keyword, String type);

}
