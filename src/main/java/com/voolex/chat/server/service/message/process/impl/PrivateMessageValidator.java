package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.exception.ValidationUserMessageException;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Класс валидирует входящее сообщение
 * Проверяется совпадение присланого senderId и текущим ID пользователя
 * Проверяется payload на пустоту
 */
@Service
@Slf4j
public class PrivateMessageValidator implements PrivateMessageInboundHandler {

    @Override
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        log.info("validate private message...");
        ChatUser chatUser = privateMessageHandlerInfo.getCurrentChatUser();
        UserMessage userMessage = privateMessageHandlerInfo.getUserMessage();

        if(chatUser.getId() != userMessage.getSenderId()) {
            throw new ValidationUserMessageException("principal user id does not match with userMessage id");
        }
        else if(Objects.isNull(userMessage.getPayload()) || userMessage.getPayload().isBlank()) {
            throw new ValidationUserMessageException("payload is blank");
        }

        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
