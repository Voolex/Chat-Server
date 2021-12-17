package com.voolex.chat.server.controller;

import com.voolex.chat.common.v2.dto.common.MessageType;
import com.voolex.chat.common.v2.dto.messages.AbstractPrivateMessage;
import com.voolex.chat.common.v2.dto.messages.PrivateMessagesBatch;
import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.mapper.impl.PrivateMessageMapperDefault;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.MessageFinder;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundMainHandler;
import com.voolex.chat.server.service.message.sending.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class PrivateMessagesController {

    @Autowired
    private PrivateMessageInboundMainHandler privateMessageInboundMainHandler;

    @Autowired
    private MessageFinder messageFinder;

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private PrivateMessageMapperDefault privateMessageMapperDefault;

    /**
     * Получение сообщений от пользователя
     */
    @MessageMapping("private/messages")
    public void privateMessageHandle(Authentication authentication, AbstractPrivateMessage userMessage) {
        PrivateMessageHandlerInfo privateMessageHandlerInfo =
                new PrivateMessageHandlerInfo((ChatUser) authentication.getPrincipal(), userMessage);
        privateMessageInboundMainHandler.handle(privateMessageHandlerInfo);
    }

    /**
     * Получение новых сообщений для конкретного отправителя и получателя
     * @param authentication
     * @param senderId ID отправителя
     * @param recipientId ID получателя
     */
    @MessageMapping("private/messages/{recipientId}/{senderId}")
    public void privateMessageNotificationHandle(Authentication authentication,
                                                 @DestinationVariable long recipientId,
                                                 @DestinationVariable long senderId) {

        List<PrivateMessage> privateMessages = messageFinder.findNewMessagesByRecipientAndSender(recipientId,senderId);

        messagingService.sendMessageToUser(
                ((ChatUser) authentication.getPrincipal()).getUserEntity(),
                new PrivateMessagesBatch(
                        privateMessages.stream()
                                .map(m -> privateMessageMapperDefault.toDTO(m))
                                .collect(Collectors.toList()))
        );
    }
}
