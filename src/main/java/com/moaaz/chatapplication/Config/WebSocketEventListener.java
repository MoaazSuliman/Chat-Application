package com.moaaz.chatapplication.Config;


import com.moaaz.chatapplication.chat.ChatMessage;
import com.moaaz.chatapplication.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
//@RequiredArgsConstructor
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        // ToDO - to be implemented...
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username= (String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            log.info("User With Name ==> {} Is Disconnected" , username);
            var chatMessage= ChatMessage
                    .builder()
                    .sender(username)
                    .messageType(MessageType.LEAVE)
                    .build();
            messageTemplate.convertAndSend("/topic/public" , chatMessage);
        }
    }
}
