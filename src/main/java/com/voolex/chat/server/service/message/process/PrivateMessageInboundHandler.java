package com.voolex.chat.server.service.message.process;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.model.ChatUser;

/**
 * Обработчик приватных сообщений
 */
public interface PrivateMessageInboundHandler {
    /**
     * Обработка входящего приватного сообщения
     * @param userMessage - сущность сообщения
     */
    void handle(ChatUser currentUser, UserMessage userMessage);
}
