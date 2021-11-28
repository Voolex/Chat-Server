package com.voolex.chat.server.service;

import com.voolex.chat.common.dto.messages.user.UserMessage;

/**
 * Обработчик приватных сообщений
 */
public interface PrivateMessageInboundHandler {
    /**
     * Обработка входящего приватного сообщения
     * @param userMessage - сущность сообщения
     */
    void handle(UserMessage userMessage);
}
