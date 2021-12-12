package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.service.MessagingServiceLegacy;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс для отправки уведомлений о новых сообщениях
 */
@Service
@Slf4j
public class NotificationMessageSender implements PrivateMessageInboundHandler {

    @Autowired
    private MessagingServiceLegacy messagingServiceLegacy;

    @Override
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        
        if(privateMessageHandlerInfo.getPrivateMessageNotification().isEmpty()) {
            throw new NoSuchElementException("private message notification not found");
        }

        log.info("send message to %d".formatted(privateMessageHandlerInfo.getUserMessage().getRecipientId()));

        messagingServiceLegacy.sendToUser(privateMessageHandlerInfo.getPrivateMessage().get().getRecipient(), privateMessageHandlerInfo.getUserMessage());

        // TODO

        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+4;
    }
}
