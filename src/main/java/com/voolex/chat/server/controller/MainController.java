package com.voolex.chat.server.controller;

import com.voolex.chat.common.dto.common.SubscriptionInfo;
import com.voolex.chat.common.dto.messages.InitMessage;
import com.voolex.chat.server.mapper.UserEntityMapper;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private MessagingService messagingService;

//    @SubscribeMapping("topic/s")
//    public void test(Principal principal) {
//        System.out.println("SUB");
////        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        System.out.println(principal.getName());
//    }2563

    @MessageMapping("private/messages")
    public void test2(Authentication authentication, String o) {
        System.out.println(o);
        ChatUser chatUser = (ChatUser) authentication.getPrincipal();
        System.out.println(chatUser.getUserEntity());

        InitMessage initMessage = InitMessage.builder().message("Привет пидор")
                .subscriptionInfo(new SubscriptionInfo("Заллупа", "Пупа"))
                .subscriptionInfo(new SubscriptionInfo("ddddd", "Пупа"))
                .userEntityDTO(userEntityMapper.toDTO(chatUser.getUserEntity()))
                .build();

        messagingTemplate.convertAndSendToUser("usertest2", "private/messages", initMessage);
    }
}
