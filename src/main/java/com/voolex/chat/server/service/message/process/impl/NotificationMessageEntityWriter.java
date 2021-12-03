package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.service.entityservice.PrivateMessageNotificationService;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class NotificationMessageEntityWriter implements PrivateMessageInboundHandler {

    @Autowired
    private PrivateMessageNotificationService privateMessageNotificationService;

    @Override
    @Transactional
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        log.info("write private message notification to db...");

        if(privateMessageHandlerInfo.getPrivateMessage().isEmpty()) {
            throw new NoSuchElementException("private message not found");
        }

        PrivateMessage privateMessage = privateMessageHandlerInfo.getPrivateMessage().get();

        PrivateMessageNotification privateMessageNotification = PrivateMessageNotification.builder()
                .sender(privateMessage.getSender())
                .recipient(privateMessage.getRecipient())
                .isReaded(false)
                .privateMessage(privateMessage)
                .build();

        privateMessageNotificationService.save(privateMessageNotification);

        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+2;
    }
}
