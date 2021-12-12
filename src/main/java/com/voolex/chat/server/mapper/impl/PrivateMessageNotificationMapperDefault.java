package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.v2.dto.common.NotificationMessageDTO;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.mapper.MapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageNotificationMapperDefault implements MapperDTO<PrivateMessageNotification, NotificationMessageDTO> {

    @Autowired
    private UserEntityMapperDefault userEntityMapper;

    @Override
    public NotificationMessageDTO toDTO(PrivateMessageNotification privateMessageNotification) {
        return new NotificationMessageDTO(userEntityMapper.toDTO(privateMessageNotification.getSender()), 1);
    }
}
