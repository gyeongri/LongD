package com.example.dark.service;

import com.example.dark.entity.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatMessageService {
    List<ChatMessage> findMessages(int roomName, Long lastMessageId, int size);
    void saveMessage(ChatMessage chatMessage);
}
