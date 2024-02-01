package com.example.chattest.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String roomName;

    public static ChatRoom create(String name){
        ChatRoom room=new ChatRoom();
        room.roomId= UUID.randomUUID().toString();
        //후에는 couple_list_id로 설정해주기
        room.roomName=name;
        return room;
    }
}
