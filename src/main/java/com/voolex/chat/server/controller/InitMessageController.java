package com.voolex.chat.server.controller;

import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

/**
 * Контроллер отвечает за отправку первого сообщения для инициализации клиента
 */
@Controller
@RequiredArgsConstructor
public class InitMessageController {
    private static final Logger logger = LoggerFactory.getLogger(InitMessageController.class);

    private final MessagingService messagingService;

    /**
     * Принимает запрос для инциализации настроек на клиенте
     * После рукопожатия клиент должен подписаться на этот адрес
     */
    @SubscribeMapping("{username}/init")
    public void initSubscribeHandle(@DestinationVariable String username, Authentication authentication) {
        logger.info("Пользователь %s запрашевает информацию для инициализации".formatted(username));
        ChatUser chatUser = (ChatUser) authentication.getPrincipal();
        messagingService.sendInitMessageToUser(chatUser.getUserEntity());
    }
}
