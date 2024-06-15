package com.bbogle.yanu.domain.order.repository;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserId(Long userId);
    List<OrderEntity> findAllByUserIdAndProductId(Long userId, Long productId);
}
