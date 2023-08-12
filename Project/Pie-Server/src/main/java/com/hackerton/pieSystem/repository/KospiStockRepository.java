package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.KospiStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KospiStockRepository extends JpaRepository<KospiStock, Long> {

}
