package com.example.chattest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK
    }

    private MessageType type;
    //방 id
    private String roomId;
    //메세지 보낸 사람
    private String sender;
    //메세지 내용
    private String message;
    //메세지 보낸 날짜
    private LocalDate sentDate;
    //메세지 보낸 시간
    private LocalTime sentTime;

}
