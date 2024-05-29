package com.bbogle.yanu.domain.order.repository;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
