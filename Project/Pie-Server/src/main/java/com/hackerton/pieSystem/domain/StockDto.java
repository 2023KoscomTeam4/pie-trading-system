package com.hackerton.pieSystem.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockDto {
    private Long marketSum;
    private Double per;
    private Double eps;
    private Double pbr;
    private Long now;
    private Long diff;
    private Double rate;
    private Long quant;
    private Long amount;
    private Long high;
    private Long low;
    private Integer risefall;
}
