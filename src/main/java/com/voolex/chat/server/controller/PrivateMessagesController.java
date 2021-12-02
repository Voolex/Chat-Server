package com.voolex.chat.server.controller;

import com.voolex.chat.common.dto.messages.user.AbstractUserMessage;
import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.message.process.PrivateMessageInboundHandler;
import lombok.extern.slf4j.Slf4j;
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
    public void privateMessageHandle(Authentication authentication, AbstractUserMessage userMessage) {
        log.info("private/messages ");
        privateMessageInboundHandler.handle((ChatUser) authentication.getPrincipal(), userMessage);
//        var user = userEntityRepository.findById(userMessage.getRecipientId());
//        if(user.isPresent()) {
//
//        } else {
//            log.info("user ID %d not found".formatted(userMessage.getRecipientId()));
//        }
    }
}
