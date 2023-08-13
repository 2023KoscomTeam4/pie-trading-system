package com.hackerton.pieSystem.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomMemberResponseDto {

    private String roomId;
    private String no;
    private String stockName;
    private String price;
    private Integer pricePercent;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer personCnt;
    private Integer totalPercent;
    private String myRoomMemberId;
    private String myUserId;
    private Double myTradingCnt;
    private Integer myPersonPercent;
    private String myContractCmpYn;
    private List<RoomMember> roomMemberList;
    // 기타 Room과 RoomMember의 정보 필드

    // 생성자
    public RoomMemberResponseDto(RoomMember roomMember, Room room, List<RoomMember> roomMemberList) {
        this.roomId = room.getRoomId();
        this.no = room.getNo();
        this.stockName = room.getStockName();
        this.price = room.getPrice();
        this.pricePercent = room.getPricePercent();
        this.personCnt = room.getPersonCnt();
        this.totalPercent = room.getTotalPercent();
        this.myRoomMemberId = roomMember.getId();
        this.myUserId = roomMember.getUserId();
        this.myTradingCnt = roomMember.getTradingCnt();
        this.myPersonPercent = roomMember.getPersonPercent();
        this.myContractCmpYn = roomMember.getContractCmpYn();
        this.roomMemberList = roomMemberList;
        Integer priceValue = Integer.parseInt(this.price.replaceAll(",", ""));
        this.minPrice = (int) (priceValue - priceValue * (this.pricePercent / 100.0));
        this.maxPrice = (int) (priceValue + priceValue * (this.pricePercent / 100.0));
    }

}