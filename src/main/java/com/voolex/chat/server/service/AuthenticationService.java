package com.voolex.chat.server.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Класс, слудащий для проверки соответствия паролей
 * и аутентификации пользователя
 */
public interface AuthenticationService {

    /**
     * @param userDetails
     * @param password    пароль, с которым необходимо сравнить userDetails
     * @return true в случае успешной аутентификации и false в случае неуспеха
     */
    boolean attemptAuthenticate(UserDetails userDetails, String password);
}