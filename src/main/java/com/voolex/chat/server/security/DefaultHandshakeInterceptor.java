package com.voolex.chat.server.security;

import com.voolex.chat.server.service.AuthenticationService;
import com.voolex.chat.server.service.impl.UserDetailServiceMySQL;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Класс необходим для перехвата запроса рукопожатия и аутентификации пользователя
 */
@Component
public class DefaultHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private UserDetailServiceMySQL userDetailsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        AuthHeaders authHeaders = getHeadersFromHttpRequest(serverHttpRequest);
        UserDetails principal = userDetailsService.loadUserByUsername(authHeaders.getUsername());
        return authenticationService.attemptAuthenticate(principal, authHeaders.getPassword());
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

    /**
     * Класс содержит информацию, необходимую для аутентификации
     */
    @Getter
    @RequiredArgsConstructor
    private static final class AuthHeaders {
        private final String username;
        private final String password;
    }

    public AuthHeaders getHeadersFromHttpRequest(ServerHttpRequest serverHttpRequest) {
        var headers = serverHttpRequest.getHeaders();
        var uHeaders = headers.get("username");
        var pHeaders = headers.get("password");
        if(uHeaders == null || pHeaders == null) {
            // TODO Либо найти, либо написать свое исключение для этого случая
            throw new AuthenticationException("auth headers not set") {

            };
        }
        return new AuthHeaders(uHeaders.get(0), pHeaders.get(0));
    }
}
