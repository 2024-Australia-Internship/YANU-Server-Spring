package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByFarmId(Long id);
}
