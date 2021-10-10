package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceMySQL implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var userEntity = userEntityRepository.findByUsername(s);
        if(userEntity.isPresent()) {
            return new ChatUser(userEntity.get(),Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            System.out.println("exception");
            throw  new UsernameNotFoundException("username <" + s + "> not found");
        }
    }
}
