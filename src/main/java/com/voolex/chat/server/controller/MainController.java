package com.voolex.chat.server.controller;

import com.voolex.chat.common.dto.messages.user.TextUserMessage;
import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private MessagingService messagingService;

//    @SubscribeMapping("topic/s")
//    public void test(Principal principal) {
//        System.out.println("SUB");
////        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        System.out.println(principal.getName());
//    }2563

    @MessageMapping("private/messages")
    public void test2(Authentication authentication, TextUserMessage userMessage) {
        var user = userEntityRepository.findById(userMessage.getRecipientId());
        if(user.isPresent()) {
            messagingService.sendToUser(user.get(), userMessage);
        } else {
            logger.info("Пользователь %d не найден в БД".formatted(userMessage.getRecipientId()));
        }
    }
}
