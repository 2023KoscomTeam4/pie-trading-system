package com.hackerton.pieSystem.domain;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String roomId;
    private String no;
    private String stockName;
    private String price;
    private Integer pricePercent;
    private Integer personCnt;
    private Integer totalPercent;


    public static ChatRoom create(String no, String stockName, String price, Integer pricePercent, Integer personPercent ) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.no = no;
        room.stockName = stockName;
        room.price = price;
        room.pricePercent = pricePercent;
        room.personCnt = 1;
        room.totalPercent = personPercent;
        return room;
    }
}