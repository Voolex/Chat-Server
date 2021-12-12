package com.voolex.chat.server.service;

import com.voolex.chat.common.v2.dto.messages.AbstractPrivateMessage;
import com.voolex.chat.common.v2.dto.messages.InitMessage;
import com.voolex.chat.server.entity.UserEntity;

/**
 * Класс отправки сообщений пользователям
 */
public interface MessagingServiceLegacy {

    /**
     * Метод отправки сообщений конкретному пользователю
     * @param userEntity пользователь, которому надо отправить сообщение
     * @param message объект сообщения
     * @param <T>
     */
    public <T extends AbstractPrivateMessage> void sendToUser(UserEntity userEntity, T message);

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
