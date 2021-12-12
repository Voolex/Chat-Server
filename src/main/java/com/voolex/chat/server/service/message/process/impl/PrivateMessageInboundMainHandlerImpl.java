package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundMainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SortedSet;

@Service
@Slf4j
public class PrivateMessageInboundMainHandlerImpl implements PrivateMessageInboundMainHandler {

    @Autowired
    private SortedSet<PrivateMessageInboundHandler> privateMessageHandlers;

    @Override
    @Transactional
    public void handle(PrivateMessageHandlerInfo privateMessageHandlerInfo) {
        privateMessageHandlers.stream().
                filter(PrivateMessageInboundHandler::enabled).
                forEach(privateMessageInboundHandler -> {
                    privateMessageInboundHandler.handle(privateMessageHandlerInfo);
                });
    }
}
