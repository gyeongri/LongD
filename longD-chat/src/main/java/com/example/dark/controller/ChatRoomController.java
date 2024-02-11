package com.example.dark.controller;

import com.example.dark.dto.ChatRoomCreateRequest;
import com.example.dark.entity.ChatMessage;
import com.example.dark.entity.ChatRoom;
import com.example.dark.service.ChatMessageService;
import com.example.dark.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/chat")
@CrossOrigin(originPatterns = {"http://i10d206.p.ssafy.io:3001/","https://i10d206.p.ssafy.io/","https://i10d206.p.ssafy.io:3001/","http://localhost:3001","http://172.28.192.1:5173/","http://192.168.35.112:5173/"})
public class ChatRoomController {

    @Autowired
    private SimpMessageSendingOperations sendingOperations;
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/test")
    public String roomDetail(Model model, @PathVariable int roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/room";
    }
    @PostMapping("/room")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequest request) {
        try {
            ChatRoom chatRoom = chatRoomService.createChatRoom(request.getRoomName());
            return ResponseEntity.ok(chatRoom);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("방 생성 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/room")
    public ResponseEntity<?> getChatRooms() {
        try {
            List<ChatRoom> chatRooms = chatRoomService.findAllRooms();
            return ResponseEntity.ok(chatRooms);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("방 목록 조회 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/room/{roomName}")
    public ResponseEntity<?> getChatRoom(@PathVariable int roomName) {
        try {
            Optional<ChatRoom> chatRoom = chatRoomService.findByRoomName(roomName);
            return chatRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("방 조회 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/messages/{roomName}")
    public ResponseEntity<?> getMessages(@PathVariable int roomName, @RequestParam(value = "lastMessageId", required = false) Long lastMessageId, @RequestParam(value = "size", defaultValue = "20") int size) {
        try {
            List<ChatMessage> messages = chatMessageService.findMessages(roomName, lastMessageId, size);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("메시지 조회 중 오류가 발생했습니다.");
        }
    }

    @MessageMapping("/chat/message")
    @CrossOrigin(origins = "*")
    public void broadcastMessage(ChatMessage chatMessage) {
        if (chatMessage.getContent() != null && !chatMessage.getContent().trim().isEmpty()) {
            chatMessageService.saveMessage(chatMessage);
            sendingOperations.convertAndSend("/topic/chat/room/" + chatMessage.getRoomName(), chatMessage);
        }
    }



}
