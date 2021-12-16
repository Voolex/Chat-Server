package com.voolex.chat.server.service.entityservice;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface PrivateMessageService {

    /**
     * Кол-во записей на странице по умолчанию
     */
    int DEFAULT_PAGE_SIZE = 50;

    /**
     * Поиск сообщений по UserEntity отправителя
     * Сообщения сортируются по дате
     * @param sender UserEntity отправителя {@link com.voolex.chat.server.entity.UserEntity}
     * @param page номер страницы, которую необходимо получить
     * @param size кол-во сообщений на одной странице
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findBySender(UserEntity sender, int page, int size);

    /**
     * Поиск сообщений по ID отправителя
     * @param senderId ID отправителя
     * @param page номер страницы, которую необходимо получить
     * @param size кол-во сообщений на одной странице
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findBySender(long senderId, int page, int size);

    /**
     * Поиск сообщений по UserEntity отправителя с кол-вом записей на странице по умолчанию  {@link #DEFAULT_PAGE_SIZE}
     * <p> Сообщения сортируются по дате
     * @param sender UserEntity отправителя {@link com.voolex.chat.server.entity.UserEntity}
     * @param page номер страницы, которую необходимо получить
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findBySender(UserEntity sender, int page);

    /**
     * Поиск сообщений по ID отправителя с кол-вом записей на странице по умолчанию
     * @param senderId ID отправителя
     * @param page номер страницы, которую необходимо получить
     * @return список сообщений пользователя
     */
    List<PrivateMessage> findBySender(long senderId, int page);

    List<PrivateMessage> findBySenderAndRecipient(UserEntity sender, UserEntity recipient, int page, int size);

    List<PrivateMessage> findBySenderAndRecipient(UserEntity sender, UserEntity recipient, int page);

    List<PrivateMessage> findBySenderAndRecipient(long senderId, long recipientId, int page, int size);

    List<PrivateMessage> findBySenderAndRecipient(long senderId, long recipientId, int page);

    /**
     * Поиск сообщения по его ID
     * @param id ID сообщения
     * @return найденное сообщение
     */
    Optional<PrivateMessage> findById(String id);

    PrivateMessage save(PrivateMessage privateMessage);
}
