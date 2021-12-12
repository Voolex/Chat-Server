package com.voolex.chat.server.service.message.sending;

import com.voolex.chat.common.v2.dto.messages.BaseMessage;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public <T extends BaseMessage> void sendMessageToUser(UserEntity userEntity, T message) {
        messagingTemplate.convertAndSendToUser(userEntity.getUsername(), message.getMessageType().getDestination(), message);
    }
}
