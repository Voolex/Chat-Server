package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.messages.BaseMessage;
import com.voolex.chat.common.dto.messages.InitMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceDefault implements MessagingService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private InitMessageCreatorService initMessageCreatorService;

    @Override
    public <T extends BaseMessage> void sendToUser(UserEntity userEntity, T message) {
        messagingTemplate.convertAndSendToUser(userEntity.getUsername(), "private/messages", message);
    }

    @Override
    public void sendInitMessageToUser(UserEntity userEntity, InitMessage message) {
        sendToUser(userEntity,"", message);
    }

    @Override
    public void sendInitMessageToUser(UserEntity userEntity) {
        sendToUser(
                userEntity,
                "init",
                initMessageCreatorService.createInitMessage(userEntity)
        );
    }

    /**
     * Общий метод для отправки сообщений конкретному пользователю
     * @param userEntity сущность пользователя
     * @param dest адрес назначения
     * @param message сущность сообщения
     * @param <T>
     */
    private <T extends BaseMessage> void sendToUser(UserEntity userEntity, String dest, T message) {
        messagingTemplate.convertAndSendToUser(userEntity.getUsername(), dest, message);
    }
}
