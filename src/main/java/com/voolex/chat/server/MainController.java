package com.voolex.chat.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MainController {

    @SubscribeMapping("topic/s")
    public void test(Principal principal) {
        System.out.println("SUB");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(principal.getName());
    }


    @MessageMapping("xuj")
    public void test2(Authentication authentication) {
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());
        System.out.println("XUJ");
    }
}
