package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.v2.dto.common.MessageType;
import com.voolex.chat.common.v2.dto.common.SubscriptionInfo;
import com.voolex.chat.common.v2.dto.messages.InitMessage;
import com.voolex.chat.server.common.BuildInfo;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.impl.UserEntityMapperDefault;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.entityservice.UserDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class InitMessageCreatorServiceDefault implements InitMessageCreatorService {

    @Autowired
    private UserEntityMapperDefault userEntityMapper;

    @Autowired
    private UserDialogService userDialogService;

    @Autowired
    private BuildInfo buildInfo;

    @Override
    @Transactional
    public InitMessage createInitMessage(UserEntity userEntity) {
        InitMessage initMessage = InitMessage.builder()
                .message("Init message")
                .serverVersion(buildInfo.getVersion())
                .subscriptionInfos(getSubscriptionsInfo(userEntity))
                .userEntityDTO(userEntityMapper.toDTO(userEntity))
                .userDialogs(userDialogService.findByUserEntity(userEntity))
                .build();

        userDialogService.findByUserEntity(userEntity).forEach(System.out::println);

        return initMessage;
    }

    private Iterable<String> getDestinations(UserEntity userEntity) {
        return Arrays.stream(MessageType.values()).
                map(messageType -> "/user/" + userEntity.getUsername() + messageType.getDestination()).
                collect(Collectors.toList());
    }

    private List<SubscriptionInfo> getSubscriptionsInfo(UserEntity userEntity) {
        return Arrays.stream(MessageType.values())
                .filter(messageType -> !messageType.equals(MessageType.INIT))
                .map(messageType -> new SubscriptionInfo(
                        "/user/" + userEntity.getUsername() + messageType.getDestination(),
                        messageType.getDescription())
                )
                .collect(Collectors.toList());
    }
}
