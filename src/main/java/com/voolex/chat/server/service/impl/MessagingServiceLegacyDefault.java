package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.messages.BaseMessage;
import com.voolex.chat.common.dto.messages.server.InitMessage;
import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.MessagingServiceLegacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.voolex.chat.common.constants.destinations.Destinations.SERVER_MESSAGE_DESTINATION;

@Service
public class MessagingServiceLegacyDefault implements MessagingServiceLegacy {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private InitMessageCreatorService initMessageCreatorService;

    @Override
    public <T extends UserMessage> void sendToUser(UserEntity userEntity, T message) {
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
                SERVER_MESSAGE_DESTINATION,
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
