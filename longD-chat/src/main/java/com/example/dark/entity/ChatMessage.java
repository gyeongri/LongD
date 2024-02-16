package com.example.dark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomName;

    private String senderId;

    private String content;

    //생성될 때 현재 시간을 저장함
    @CreationTimestamp
    private LocalDateTime createdAt;

}
