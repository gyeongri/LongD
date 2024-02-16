package com.example.dark.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //UUID로 만드는 방 식별자
    private String roomId= UUID.randomUUID().toString();

    //아마 cpi
    private int roomName;

    //생성될때 현재 시간을 저장함
    @CreationTimestamp
    private LocalDateTime createdAt;

}