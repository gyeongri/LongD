package com.example.dark.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageCreateRequest {
    private String roomName;
    private String senderId;
    private String content;
}
