package com.voolex.chat.server.controller;

import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

/**
 * Контроллер отвечает за обработку технических серверных сообщений,
 * не связанных с сообщениями пользователей
 */
@Controller
@RequiredArgsConstructor
public class ServerMessageController {
    private static final Logger logger = LoggerFactory.getLogger(ServerMessageController.class);

    private final MessagingService messagingService;

    /**
     * Принимает запрос на подписку получения команд от сервера
     * В ответ получает сообщение инициализации
     * После рукопожатия клиент должен подписаться на этот адрес
     */
    @SubscribeMapping("{username}/server")
    public void initSubscribeHandle(@DestinationVariable String username, Authentication authentication) {
        logger.info("Пользователь %s запрашевает информацию для инициализации".formatted(username));
        ChatUser chatUser = (ChatUser) authentication.getPrincipal();
        messagingService.sendInitMessageToUser(chatUser.getUserEntity());
    }
}