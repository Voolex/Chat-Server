package com.voolex.chat.server.service.impl;

import com.voolex.chat.server.security.DefaultHandshakeInterceptor;
import com.voolex.chat.server.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSimpleService implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSimpleService.class);

    @Autowired
    private BCryptPasswordEncryptionService passwordEncryptionService;

    /**
     * Метод выполняет попытку аутентификации
     * Происходит сравнение пароля, который ввел пользователь и пароля в БД
     * @param userDetails сущность пользователя
     * @param password    пароль, с которым необходимо сравнить userDetails
     * @return true если пароли равны, false в противном случае
     */
    @Override
    public boolean attemptAuthenticate(UserDetails userDetails, String password) {
        logger.debug("Попытка аутентификации пользователя [%s]...".formatted(userDetails.getUsername()));
        if(passwordEncryptionService.matches(password, userDetails.getPassword())) {
            logger.debug("Пользователь [%s] успешно аутентифицирован! Создаем объект Authentication...".formatted(userDetails.getUsername()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            logger.debug("Успешно! Конфигурируем securityContext...");
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            logger.debug("Успешно!");
            return true;
        } else {
            logger.debug("Пользователь [%s] предоставил неверные учетные данные".formatted(userDetails.getUsername()));
            throw new BadCredentialsException("wrong username or password");
        }
    }
}
