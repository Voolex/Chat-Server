package com.voolex.chat.server.service.message.sending;

import com.voolex.chat.common.v2.dto.messages.BaseMessage;
import com.voolex.chat.server.entity.UserEntity;

/**
 * Сервис отправки сообщений пользователям
 */
public interface MessagingService {

    /**
     * Отпавляет сообщение пользователю
     * @param userEntity пользователь, которому необходимо отправить сообщение
     * @param message сообщение, которое необходимо отправить
     * @param <T> сообщение, которое необходимо отправить
     */
    <T extends BaseMessage> void sendMessageToUser(UserEntity userEntity, T message);
}
