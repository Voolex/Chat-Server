package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.v2.dto.messages.AbstractPrivateMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import com.voolex.chat.server.service.entityservice.UserEntityService;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Класс создает Entity сообщения и записывает его в базу данных
 */
@Service
@Slf4j
public class PrivateMessageEntityWriter implements PrivateMessageInboundHandler {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PrivateMessageService privateMessageService;

    @Override
    @Transactional
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        log.info("write private message to db...");
        AbstractPrivateMessage userMessage = privateMessageHandlerInfo.getUserMessage();

        PrivateMessage privateMessage = PrivateMessage.builder()
                .payloadType(userMessage.getPayloadType())
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

        privateMessageHandlerInfo.setPrivateMessage(Optional.of(privateMessage));
        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+1;
    }
}
