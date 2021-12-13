package com.voolex.chat.server.service.message.sending;

import com.voolex.chat.common.v2.dto.messages.BaseMessage;
import com.voolex.chat.server.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessagingServiceImpl implements MessagingService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public <T extends BaseMessage> void sendMessageToUser(UserEntity userEntity, T message) {
        log.info("send message to user [%s] [%s] ".formatted(userEntity.getId(), message.getMessageType().getDestination()));
        messagingTemplate.convertAndSendToUser(userEntity.getUsername(), message.getMessageType().getDestination(), message);
    }
}
