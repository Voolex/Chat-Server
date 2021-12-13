package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.v2.dto.common.NotificationMessageDTO;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.mapper.MapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateMessageNotificationMapperDefault implements MapperDTO<PrivateMessageNotification, NotificationMessageDTO> {

    @Autowired
    private UserEntityMapperDefault userEntityMapper;

    @Override
    public NotificationMessageDTO toDTO(PrivateMessageNotification privateMessageNotification) {
        return new NotificationMessageDTO(userEntityMapper.toDTO(privateMessageNotification.getSender()), 1);
    }

    /**
     * Метод конвертирует список уведомлений от одного пользователя в один DTO
     * sender в privateMessageNotifications должен быть одинаковым для корректной конвертации
     * amount будет равен размеру списка
     * @param privateMessageNotifications список уведомлений
     * @return DTO
     */
    public NotificationMessageDTO toDTO(List<PrivateMessageNotification> privateMessageNotifications) {
        if(privateMessageNotifications == null || privateMessageNotifications.isEmpty()) {
            throw new IllegalArgumentException("privateMessageNotifications is empty or null");
        }

        return new NotificationMessageDTO(
                userEntityMapper.toDTO(
                        privateMessageNotifications.get(0).getSender()), privateMessageNotifications.size());
    }
}
