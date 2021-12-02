package com.voolex.chat.server.service.security;

import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.service.entityservice.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailServiceDefault implements UserDetailsService {

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Загрузка пользователя [%s] из БД...".formatted(s));

        var userEntity = userEntityService.findByUsername(s);
        if(userEntity.isPresent()) {
            // TODO Временное решение выдавать всем роль ROLE_USER, пока не придумана ролевая модель
            log.debug("Пользователь [%s] найден. Создаем UserDetails...".formatted(userEntity.get().getUsername()));
            return new ChatUser(userEntity.get(),Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            log.debug("Пользователь с именем [%s] не найден в БД".formatted(s));
            throw  new UsernameNotFoundException("username <" + s + "> not found");
        }
    }
}
