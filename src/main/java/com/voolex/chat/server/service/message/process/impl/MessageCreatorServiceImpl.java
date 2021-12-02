package com.voolex.chat.server.service.message.process.impl;

import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.model.ChatUser;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import com.voolex.chat.server.service.entityservice.UserEntityService;
import com.voolex.chat.server.service.message.process.MessageCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;

@Service
public class MessageCreatorServiceImpl implements MessageCreatorService {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PrivateMessageService privateMessageService;

    @Override
    @Transactional
    public PrivateMessage messageCreate(UserMessage userMessage) {

        PrivateMessage privateMessage = PrivateMessage.builder()
                .messageType(userMessage.getMessageType())
                .dateTime(LocalDateTime.now())
                .sender(userEntityService.findById(userMessage.getSenderId()).orElseThrow(
                        () -> new UsernameNotFoundException("User with id %d not found".formatted(userMessage.getRecipientId())))
                )
                .recipient(userEntityService.findById(userMessage.getRecipientId()).orElseThrow(
                        () -> new UsernameNotFoundException("User with id %d not found".formatted(userMessage.getRecipientId())))
                )
                .payload(userMessage.getPayload())
                .build();

        privateMessageService.save(privateMessage);

        return privateMessage;
    }
}
