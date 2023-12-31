package com.hackerton.pieSystem.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    //채팅파이 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //내용
    private String message;
}