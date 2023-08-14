package com.hackerton.pieSystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Room {

    private  @Id
    @Column  String roomId;
    @Column  String no;
    @Column  String stockName;
    @Column  String price;
    @Column  Integer pricePercent;
    @Column  Integer personCnt;
    @Column  Integer totalPercent;
    @Column LocalDateTime createdAt;

}