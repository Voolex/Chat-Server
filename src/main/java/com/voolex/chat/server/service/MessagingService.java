package com.voolex.chat.server.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.voolex.chat.common.dto.messages.BaseMessage;
import com.voolex.chat.common.dto.messages.InitMessage;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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
    public <T extends BaseMessage> void sendToUser(UserEntity userEntity, T message);

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
