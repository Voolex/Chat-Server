package com.voolex.chat.server.service;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * Класс отвечает за поиск сообщений
 */
public interface MessageFinder {
    /**
     * Поиск новых сообщений по отправителю и получателю
     * @param recipient получатель
     * @param sender отправитель
     * @return список сообщений
     */
    List<PrivateMessage> findNewMessagesByRecipientAndSender(long recipient, long sender);
}
