package com.example.dark.repository;

import com.example.dark.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Integer> {
    Page<ChatMessage> findByRoomName(int roomName, Pageable pageable);

    List<ChatMessage> findByRoomNameAndIdLessThan(int roomName, Long lastMessageId, PageRequest id);
}
