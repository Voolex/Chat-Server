package com.voolex.chat.server.service;

import com.voolex.chat.common.dto.messages.server.InitMessage;
import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.entity.UserEntity;

/**
 * Класс отправки сообщений пользователям
 */
public interface MessagingService {

    /**
     * Метод отправки сообщений конкретному пользователю
     * @param userEntity пользователь, которому надо отправить сообщение
     * @param message объект сообщения
     * @param <T>
     */
    public <T extends UserMessage> void sendToUser(UserEntity userEntity, T message);

    /**
     * Метод отправки сообщения инициализации пользователю
     * @param userEntity пользователь, которому надо отправить сообщение
     * @param message объект сообщения
     */
    public void sendInitMessageToUser(UserEntity userEntity, InitMessage message);

    /**
     * Метод отправки сообщения инициализации пользователю
     * @param userEntity пользователь, которому надо отправить сообщение
     */
    public void sendInitMessageToUser(UserEntity userEntity);
}
