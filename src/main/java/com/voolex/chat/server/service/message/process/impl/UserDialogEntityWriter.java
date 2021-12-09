package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.service.entityservice.UserDialogService;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис проверяет, есть ли диалог между пользователями и создает его, если нет
 */
@Service
@Slf4j
public class UserDialogEntityWriter implements PrivateMessageInboundHandler {

    @Autowired
    private UserDialogService userDialogService;

    @Override
    public PrivateMessageHandlerInfo handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        if(!userDialogService.existByUserIdAndWithUserId(
                privateMessageHandlerInfo.getUserMessage().getSenderId(),
                privateMessageHandlerInfo.getUserMessage().getRecipientId())) {

            UserDialog userDialog = UserDialog.builder()
                    .userEntity(privateMessageHandlerInfo.getPrivateMessage().get().getSender())
                    .withUserEntity(privateMessageHandlerInfo.getPrivateMessage().get().getRecipient())
                    .lastMessageDateTime(privateMessageHandlerInfo.getPrivateMessage().get().getDateTime())
                    .build();
            userDialogService.save(userDialog);
        }
        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+3;
    }
}
