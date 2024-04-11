package com.bbogle.yanu.repository;


import com.bbogle.yanu.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByTitleContaining(String keyword);

}
