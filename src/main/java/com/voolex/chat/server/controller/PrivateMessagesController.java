package com.voolex.chat.server.controller;

import com.voolex.chat.common.dto.messages.user.TextUserMessage;
import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.MessagingService;
import com.voolex.chat.server.service.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class PrivateMessagesController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PrivateMessageInboundHandler privateMessageInboundHandler;

//    @SubscribeMapping("topic/s")
//    public void test(Principal principal) {
//        System.out.println("SUB");
////        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        System.out.println(principal.getName());
//    }

    @MessageMapping("private/messages")
    public void test2(Authentication authentication, UserMessage userMessage) {
        privateMessageInboundHandler.handle(userMessage);
//        var user = userEntityRepository.findById(userMessage.getRecipientId());
//        if(user.isPresent()) {
//
//        } else {
//            log.info("user ID %d not found".formatted(userMessage.getRecipientId()));
//        }
    }
}
