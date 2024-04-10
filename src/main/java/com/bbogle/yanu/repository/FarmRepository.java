package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<FarmEntity, Long> {
    FarmEntity findByUserId(Long user_id);
}
