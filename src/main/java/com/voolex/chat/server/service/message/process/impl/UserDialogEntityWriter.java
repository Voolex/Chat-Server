package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.repository.UserDialogsRepository;
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
        if(!userDialogService.isUserDialogExist(
                privateMessageHandlerInfo.getUserMessage().getSenderId(),
                privateMessageHandlerInfo.getUserMessage().getRecipientId())) {

        }
        return privateMessageHandlerInfo;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+3;
    }
}
