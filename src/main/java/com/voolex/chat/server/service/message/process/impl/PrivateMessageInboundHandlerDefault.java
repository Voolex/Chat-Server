package com.voolex.chat.server.service.message.process.impl;


import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.exception.ValidationUserMessageException;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.message.process.MessageCreatorService;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PrivateMessageInboundHandlerDefault implements PrivateMessageInboundHandler {

    @Autowired
    private MessageCreatorService messageCreatorService;

    @Override
    public void handle(ChatUser currentUser, UserMessage userMessage) {
        try {
            validateUserMessage(currentUser, userMessage);
            messageCreatorService.messageCreate(userMessage);

        } catch (ValidationUserMessageException e) {
            log.error("error handling message from user - " + e.getMessage(), e);
        }
    }

    /**
     * Метод валидирует сущность сообщения
     */
    private void validateUserMessage(ChatUser chatUser, UserMessage userMessage) {
        if(chatUser.getId() != userMessage.getSenderId()) {
            throw new ValidationUserMessageException("principal user id does not match with userMessage id");
        }
        else if(Objects.isNull(userMessage.getPayload()) || userMessage.getPayload().isBlank()) {
            throw new ValidationUserMessageException("payload is blank");
        }
    }
}
