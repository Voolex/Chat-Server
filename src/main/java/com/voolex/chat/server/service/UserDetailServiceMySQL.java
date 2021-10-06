package com.voolex.chat.server.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceMySQL implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s.equals("user1")) {
            User user = new User("user1", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_PIDOR")));
            return user;
        } else {
            System.out.println("exception");
            throw  new UsernameNotFoundException("username <" + s + "> not found");
        }
    }
}
