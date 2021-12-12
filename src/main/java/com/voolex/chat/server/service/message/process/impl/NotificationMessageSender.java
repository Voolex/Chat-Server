package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.v2.dto.messages.NotificationMessage;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.mapper.impl.PrivateMessageNotificationMapperDefault;
import com.voolex.chat.server.service.MessagingServiceLegacy;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import com.voolex.chat.server.service.message.sending.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс для отправки уведомлений о новых сообщениях
 */
@Service
@Slf4j
public class NotificationMessageSender implements PrivateMessageInboundHandler {

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private PrivateMessageNotificationMapperDefault privateMessageNotificationMapper;

    @Override
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        
        if(privateMessageHandlerInfo.getPrivateMessageNotification().isEmpty()) {
            throw new NoSuchElementException("private message notification not found");
        }

        log.info("send notification message to user [%d | ]".formatted(privateMessageHandlerInfo.getUserMessage().getRecipientId()));

        messagingService.sendMessageToUser(
                privateMessageHandlerInfo.getPrivateMessage().get().getRecipient(),
                createNotificationMessage(privateMessageHandlerInfo.getPrivateMessageNotification().get())
        );

//        messagingServiceLegacy.sendToUser(privateMessageHandlerInfo.getPrivateMessage().get().getRecipient(), privateMessageHandlerInfo.getUserMessage());

        // TODO

        return privateMessageHandlerInfo;
    }

    private NotificationMessage createNotificationMessage(PrivateMessageNotification privateMessageNotification) {
        return new NotificationMessage(
                Collections.singletonList(privateMessageNotificationMapper.toDTO(privateMessageNotification))
        );
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+4;
    }
}
