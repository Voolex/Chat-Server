package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import com.voolex.chat.server.service.entityservice.UserEntityService;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandlerV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Класс создает Entity сообщения и записывает его в базу данных
 */
@Service
public class PrivateMessageEntityWriter implements PrivateMessageInboundHandlerV {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PrivateMessageService privateMessageService;

    @Override
    @Transactional
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        UserMessage userMessage = privateMessageHandlerInfo.getUserMessage();

        PrivateMessage privateMessage = PrivateMessage.builder()
                .messageType(userMessage.getMessageType())
                .dateTime(LocalDateTime.now())
                .sender(userEntityService.findById(userMessage.getSenderId()).orElseThrow(
                        () -> new UsernameNotFoundException("User with id %d not found".formatted(userMessage.getRecipientId())))
                )
                .recipient(userEntityService.findById(userMessage.getRecipientId()).orElseThrow(
                        () -> new UsernameNotFoundException("User with id %d not found".formatted(userMessage.getRecipientId())))
                )
                .payload(userMessage.getPayload())
                .build();

        privateMessageService.save(privateMessage);

        privateMessageHandlerInfo.setPrivateMessage(privateMessage);
        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE-2;
    }
}
