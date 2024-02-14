package com.example.dark.repository;

import com.example.dark.entity.ChatMessage;
import com.example.dark.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Integer> {
    Optional<ChatRoom> findByRoomName(int roomName);
}
