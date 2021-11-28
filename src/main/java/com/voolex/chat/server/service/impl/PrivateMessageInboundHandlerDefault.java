package com.voolex.chat.server.service.impl;


import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.exception.ValidationUserMessageException;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PrivateMessageInboundHandlerDefault implements PrivateMessageInboundHandler {

    @Override
    public void handle(UserMessage userMessage) {
        try {
            validateUserMessage(userMessage);


        } catch (ValidationUserMessageException e) {
            log.error("error handling message from user", e);
        }
    }

    /**
     * Метод валидирует сущность сообщения
     * @return
     */
    private void validateUserMessage(UserMessage userMessage) {
        ChatUser chatUser = (ChatUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(chatUser.getId() != userMessage.getSenderId()) {
            throw new ValidationUserMessageException("principal user id does not match with userMessage id");
        }
        else if(Objects.isNull(userMessage.getPayload()) || userMessage.getPayload().isBlank()) {
            throw new ValidationUserMessageException("payload is blank");
        }
    }
}
