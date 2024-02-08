package com.example.dark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@CrossOrigin(originPatterns = {"http://i10d206.p.ssafy.io:3001","https://i10d206.p.ssafy.io:3001","http://localhost:5173","http://172.28.192.1:5173"})
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //chat에 socket연결하기
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("http://localhost:5173","http://i10d206.p.ssafy.io:3001","https://i10d206.p.ssafy.io:3001","http://172.28.192.1:5173").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic","/queue");

    }
}
