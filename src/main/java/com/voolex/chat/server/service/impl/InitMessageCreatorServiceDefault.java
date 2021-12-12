package com.voolex.chat.server.service.impl;

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
                .subscriptionInfo(new SubscriptionInfo(
                        createPrivateMessagesDestination(userEntity),
                        "Private messages destination")
                )
                .subscriptionInfo(new SubscriptionInfo(
                        createPrivateMessagesNotificationsDestination(userEntity),
                        "Private messages notifications destination")
                )
                .userEntityDTO(userEntityMapper.toDTO(userEntity))
                .userDialogs(userDialogService.findByUserEntity(userEntity))
                .build();

        userDialogService.findByUserEntity(userEntity).forEach(System.out::println);

        return initMessage;
    }

    private String createPrivateMessagesDestination(UserEntity userEntity) {
        return "/user/%s/private/messages".formatted(userEntity.getUsername());
    }

    private String createPrivateMessagesNotificationsDestination(UserEntity userEntity) {
        return "/user/%s/private/notifications".formatted(userEntity.getUsername());
    }
}
