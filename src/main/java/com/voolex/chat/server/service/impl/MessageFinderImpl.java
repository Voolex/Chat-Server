package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.service.MessageFinder;
import com.voolex.chat.server.service.entityservice.PrivateMessageNotificationService;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import com.voolex.chat.server.service.entityservice.UserEntityService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageFinderImpl implements MessageFinder {

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private PrivateMessageNotificationService privateMessageNotificationService;

    @Autowired
    private UserEntityService userEntityService;

    @Override
    @Transactional
    public List<PrivateMessage> findNewMessagesByRecipientAndSender(long recipient, long sender) {


        Optional<UserEntity> recipientEntityOptional = userEntityService.findById(recipient);
        Optional<UserEntity> senderEntityOptional = userEntityService.findById(sender);

        List<PrivateMessageNotification> notifications = privateMessageNotificationService.
                findAllNewNotificationsBySenderAndRecipient(
                        recipientEntityOptional.orElseThrow(
                                () -> new UsernameNotFoundException("User with id %d not found".formatted(recipient))
                        ),
                        senderEntityOptional.orElseThrow(
                                () -> new UsernameNotFoundException("User with id %d not found".formatted(recipient))
                        )
                );

        List<PrivateMessage> messages = new ArrayList<>();
        notifications.forEach(notification -> messages.add(notification.getPrivateMessage()));

        return messages;
    }
}
