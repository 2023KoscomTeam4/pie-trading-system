package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.KospiStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface KospiStockRepository extends JpaRepository<KospiStock, Long> {

    default KospiStock saveWithTimestamp(KospiStock entity) {
        // 현재 시간 설정
        entity.setCreatedAt(LocalDateTime.now());
        // save 호출
        return save(entity);
    }
}
