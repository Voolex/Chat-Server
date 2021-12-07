package com.voolex.chat.server.controller;

import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.MessagingServiceLegacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ServerMessageController {

    private final MessagingServiceLegacy messagingServiceLegacy;

    /**
     * Принимает запрос на подписку получения команд от сервера
     * В ответ получает сообщение инициализации
     * После рукопожатия клиент должен подписаться на этот адрес
     */
    @SubscribeMapping("{username}/server")
    public void initSubscribeHandle(@DestinationVariable String username, Authentication authentication) {
        log.info("Пользователь %s запрашевает информацию для инициализации".formatted(username));
        ChatUser chatUser = (ChatUser) authentication.getPrincipal();
        messagingServiceLegacy.sendInitMessageToUser(chatUser.getUserEntity());
    }
}
