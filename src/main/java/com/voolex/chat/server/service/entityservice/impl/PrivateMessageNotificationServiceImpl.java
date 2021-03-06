package com.voolex.chat.server.service.entityservice.impl;

import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.repository.PrivateMessageNotificationRepository;
import com.voolex.chat.server.service.entityservice.PrivateMessageNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrivateMessageNotificationServiceImpl implements PrivateMessageNotificationService {

    @Autowired
    private PrivateMessageNotificationRepository privateMessageNotificationRepository;

    @Override
    public PrivateMessageNotification save(PrivateMessageNotification privateMessageNotification) {
        return privateMessageNotificationRepository.save(privateMessageNotification);
    }

    @Override
    public List<PrivateMessageNotification> findAllNewNotifications(UserEntity recipient) {
        return privateMessageNotificationRepository.findAllByRecipientAndIsReaded(recipient, false);
    }

    @Override
    public List<PrivateMessageNotification> findAllByRecipientAndReaded(UserEntity recipient, boolean readed) {
        return privateMessageNotificationRepository.findAllByRecipientAndIsReaded(recipient, readed);
    }

    @Override
    public List<PrivateMessageNotification> findAllNewNotificationsBySenderAndRecipient(UserEntity sender, UserEntity recipient) {
        return privateMessageNotificationRepository.findAllBySenderAndRecipientAndIsReaded(sender, recipient, false);
    }
}
