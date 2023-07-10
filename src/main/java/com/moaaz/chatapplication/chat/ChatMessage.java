package com.moaaz.chatapplication.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType messageType;
}
