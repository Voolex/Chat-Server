package com.voolex.chat.server;

import com.voolex.chat.common.dto.InitMessage;
import com.voolex.chat.server.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @SubscribeMapping("topic/s")
//    public void test(Principal principal) {
//        System.out.println("SUB");
////        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        System.out.println(principal.getName());
//    }2563

    @MessageMapping("chat")
    public void test2(Authentication authentication, String o) {
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());
        System.out.println("XUJ");
        System.out.println(o);
        ChatUser chatUser = (ChatUser) authentication.getPrincipal();
        System.out.println(chatUser.getUserEntity());

        InitMessage initMessage = new InitMessage("ver", "tst");

        messagingTemplate.convertAndSendToUser("usertest2", "private/messages", initMessage);
    }
}
