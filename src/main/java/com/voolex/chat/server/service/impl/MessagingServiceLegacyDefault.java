package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.v2.dto.messages.AbstractPrivateMessage;
import com.voolex.chat.common.v2.dto.messages.BaseMessage;
import com.voolex.chat.common.v2.dto.messages.InitMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.MessagingServiceLegacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceLegacyDefault implements MessagingServiceLegacy {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private InitMessageCreatorService initMessageCreatorService;

    @Override
    public <T extends AbstractPrivateMessage> void sendToUser(UserEntity userEntity, T message) {
        messagingTemplate.convertAndSendToUser(userEntity.getUsername(), "private/notifications", message);
    }

    @Override
    public void sendInitMessageToUser(UserEntity userEntity, InitMessage message) {
        sendToUser(userEntity,"", message);
    }

    @Override
    public void sendInitMessageToUser(UserEntity userEntity) {
        sendToUser(
                userEntity,
                "server",
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
