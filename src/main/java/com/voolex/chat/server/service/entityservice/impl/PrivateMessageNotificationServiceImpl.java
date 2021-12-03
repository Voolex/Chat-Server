package com.voolex.chat.server.service.entityservice.impl;

import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.repository.PrivateMessageNotificationRepository;
import com.voolex.chat.server.service.entityservice.PrivateMessageNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageNotificationServiceImpl implements PrivateMessageNotificationService {

    @Autowired
    private PrivateMessageNotificationRepository privateMessageNotificationRepository;

    @Override
    public PrivateMessageNotification save(PrivateMessageNotification privateMessageNotification) {
        return privateMessageNotificationRepository.save(privateMessageNotification);
    }
}
