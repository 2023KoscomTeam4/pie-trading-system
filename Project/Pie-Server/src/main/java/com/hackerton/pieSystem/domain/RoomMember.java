package com.hackerton.pieSystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class RoomMember {
    private  @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "room_member_id") String id;
    @Column  String roomId;
    @Column  String userId;
    @Column  Integer pricePercent;
    @Column  Double tradingCnt;
    @Column  Integer personPercent;
    @Column  String contractCmpYn;
}