package com.voolex.chat.server.service.entityservice;

import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.entity.UserEntity;

import java.util.List;

public interface PrivateMessageNotificationService {

    PrivateMessageNotification save(PrivateMessageNotification privateMessageNotification);

    /**
     * Метод позволяет получить все новые уведомления для конкретного пользователя
     * @param recipient сущность пользователя
     * @return список с новыми уведомлениями
     */
    List<PrivateMessageNotification> findAllNewNotifications(UserEntity recipient);

    List<PrivateMessageNotification> findAllByRecipientAndReaded(UserEntity recipient, boolean readed);

}
