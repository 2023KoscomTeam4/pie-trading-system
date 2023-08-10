package com.hackerton.pieSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockSummary {

    private String name;
    private BigDecimal price;
    // 기타 필요한 필드를 여기에 추가하세요.

    // 기본 생성자

    // getter와 setter
}