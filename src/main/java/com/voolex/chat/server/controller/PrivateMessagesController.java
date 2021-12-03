package com.voolex.chat.server.controller;

import com.voolex.chat.common.dto.messages.user.AbstractUserMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundMainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.SortedSet;

@Controller
@Slf4j
public class PrivateMessagesController {

    @Autowired
    private PrivateMessageInboundMainHandler privateMessageInboundMainHandler;

    @MessageMapping("private/messages")
    public void privateMessageHandle(Authentication authentication, AbstractUserMessage userMessage) {
        PrivateMessageHandlerInfo privateMessageHandlerInfo =
                new PrivateMessageHandlerInfo((ChatUser) authentication.getPrincipal(), userMessage);
        privateMessageInboundMainHandler.handle(privateMessageHandlerInfo);
    }
}
