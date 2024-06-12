package com.bbogle.yanu.domain.product.repository;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByFarmId(Long id);
    List<ProductEntity> findByFarmIdIn(List<Long> farmIds);
}
