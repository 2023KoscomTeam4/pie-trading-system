package com.hackerton.pieSystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class KospiStock {

    private @Id
    @Column(name = "kospi_stock_id") String id;
    @Column String no;
    @Column String stockName;
    @Column String price;
    @Column String diffAmount;
    @Column String dayRange;
    @Column String parValue;
    @Column String marketCap;
    @Column String numberOfListedShares;
    @Column String foreignOwnRate;
    @Column String per;
    @Column String roe;
    @Column String discussionRoomUrl;
}