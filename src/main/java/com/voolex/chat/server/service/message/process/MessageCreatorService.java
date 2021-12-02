package com.voolex.chat.server.service.message.process;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.entity.PrivateMessage;

/**
 * Сервис для создания сообщений и записи их в БД
 */
public interface MessageCreatorService {

    PrivateMessage messageCreate(UserMessage userMessage);
}
