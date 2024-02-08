package com.example.dark.service;

import com.example.dark.entity.ChatRoom;
import com.example.dark.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom createChatRoom(int roomName) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public Optional<ChatRoom> findByRoomName(int roomName) {
        return chatRoomRepository.findByRoomName(roomName);
    }
}
