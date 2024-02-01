package com.example.chattest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //소켓연결 uri
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //메세지를 받을때 경로를 설정해주는 함수
        //queue는 1:1 / topic은 1:N
        //api에 접두사로 queue와 topic이 붙어있으면 messageBroker가 가로챔
        registry.enableSimpleBroker("/queue", "/topic");

        //메세지를 보낼 때 경로 설정
        //클라이언트가 메세지를 보낼 때, 경로 앞에 /app이 붙어 있으면 Broker가 가로챔
        registry.setApplicationDestinationPrefixes("/app");
    }
}
