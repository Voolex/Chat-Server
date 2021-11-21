package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.common.SubscriptionInfo;
import com.voolex.chat.common.dto.messages.server.InitMessage;
import com.voolex.chat.server.common.BuildInfo;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.UserEntityMapper;
import com.voolex.chat.server.service.InitMessageCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitMessageCreatorServiceDefault implements InitMessageCreatorService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private BuildInfo buildInfo;

    @Override
    public InitMessage createInitMessage(UserEntity userEntity) {
        InitMessage initMessage = InitMessage.builder().
                message("Init message").
                serverVersion(buildInfo.getVersion()).
                subscriptionInfo(new SubscriptionInfo(
                        createPrivateMessagesDestination(userEntity),
                        "Private messages destination")
                ).
                userEntityDTO(userEntityMapper.toDTO(userEntity)).
                build();
        return initMessage;
    }

    private String createPrivateMessagesDestination(UserEntity userEntity) {
        return "/user/%s/private/messages".formatted(userEntity.getUsername());
    }
}
