package com.voolex.chat.server.service.entityservice;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;

import java.util.List;

public interface PrivateMessageService {
    /**
     * Поиск сообщений по UserEntity отправителя
     * @param sender UserEntity отправителя
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findAllBySender(UserEntity sender);

    /**
     * Поиск сообщений по ID отправителя
     * @param userId ID отправителя
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findAllBySender(long userId);
}
