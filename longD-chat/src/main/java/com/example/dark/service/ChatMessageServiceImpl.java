package com.example.dark.service;

import com.example.dark.entity.ChatMessage;
import com.example.dark.repository.ChatMessageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public List<ChatMessage> findMessages(int roomName, Long lastMessageId, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("id").descending());
        if (lastMessageId == null) {
            // lastMessageId가 없을땐, 최근 메시지부터 조회
            return chatMessageRepository.findByRoomName(roomName, pageable).getContent();
        } else {
            // lastMessageId가 있을 때, 해당 ID 이전의 메시지 조회
            return chatMessageRepository.findByRoomNameAndIdLessThan(roomName, lastMessageId, PageRequest.of(0, size, Sort.by("id").descending()));
        }
    }
    
    
    //메시지 저장하기
    @Override
    public void saveMessage(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
