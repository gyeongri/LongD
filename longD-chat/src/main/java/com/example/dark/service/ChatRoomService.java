package com.example.dark.service;

import com.example.dark.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    ChatRoom createChatRoom(int roomName);
    List<ChatRoom> findAllRooms();
    Optional<ChatRoom> findByRoomName(int roomName);
}
