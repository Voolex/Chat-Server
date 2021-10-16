package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.security.DefaultHandshakeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceMySQL.class);

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug("Загрузка пользователя [%s] из БД...".formatted(s));
        var userEntity = userEntityRepository.findByUsername(s);
        if(userEntity.isPresent()) {
            // TODO Временное решение выдавать всем роль ROLE_USER, пока не придумана ролевая модель
            logger.debug("Пользователь [%s] найден. Создаем UserDetails...".formatted(userEntity.get().getUsername()));
            return new ChatUser(userEntity.get(),Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            logger.debug("Пользователь с именем [%s] не найден в БД".formatted(s));
            throw  new UsernameNotFoundException("username <" + s + "> not found");
        }
    }
}
