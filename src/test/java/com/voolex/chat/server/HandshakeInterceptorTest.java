package com.voolex.chat.server;

import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.repository.UserEntityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HandshakeInterceptorTest {

    public static final String HEADER_USERNAME = "username";
    public static final String HEADER_PASSWORD = "password";
    public static final String CONNECTION_URL = "ws://localhost:%d/ws-connect";



    @LocalServerPort
    private Integer port;

    @MockBean
    private UserEntityRepository userEntityRepository;

    private UserEntity userEntity;
    private WebSocketStompClient stompClient;
    private WebSocketHttpHeaders httpHeaders;

    @Before
    public void initTests() {
        userEntity = new UserEntity();
        userEntity.setUsername("user");
        userEntity.setPassword("$2a$10$o6IKBzREfW/ln1QraiEQour1caXyZ804DFnZ9A6LyRdVnInuKY8ma");

        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport( new StandardWebSocketClient()) );

        WebSocketClient client = new SockJsClient(transports);

        stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        httpHeaders = new WebSocketHttpHeaders();
        httpHeaders.add(HEADER_USERNAME, "user");
        httpHeaders.add(HEADER_PASSWORD, "123456");
    }

    /**
     * Тестирование перехватчика рукопожатия.
     * При всех необходимых хедерах тест должен быть успешно пройден.
     * @throws InterruptedException
     */
    @Test
    public void handshakeInterceptorTest() throws InterruptedException {

        Mockito.when(userEntityRepository.findByUsername(Mockito.any())).thenReturn(java.util.Optional.of(userEntity));

        final boolean[] result = {false};

        stompClient.connect(String.format(CONNECTION_URL, port), httpHeaders, new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                result[0] = true;
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {

            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {

            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {

            }
        });
        Thread.sleep(1000);

        assertTrue(result[0]);
    }
}
