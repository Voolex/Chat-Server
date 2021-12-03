package com.voolex.chat.server.service.message.process;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;

/**
 * Главный обработчик приватных сообщений
 * Запускает цепочку обработчиков
 */
public interface PrivateMessageInboundMainHandler {
    void handle(PrivateMessageHandlerInfo privateMessageHandlerInfo);
}
