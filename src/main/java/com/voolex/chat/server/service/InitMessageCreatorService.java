package com.voolex.chat.server.service;

import com.voolex.chat.common.dto.messages.InitMessage;
import com.voolex.chat.server.entity.UserEntity;

/**
 * Класс служит для создания сообщения инициализации для пользователя
 */
public interface InitMessageCreatorService {

    /**
     * Метод генерирует сообщение инициализации
     * @param userEntity сущность пользователя
     * @return InitMessage
     */
    public InitMessage createInitMessage(UserEntity userEntity);
}
