package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.Member;
import com.hackerton.pieSystem.domain.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockListRepository extends JpaRepository<StockList, Long> {

}
