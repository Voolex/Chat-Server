package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.common.SubscriptionInfo;
import com.voolex.chat.common.dto.messages.server.InitMessage;
import com.voolex.chat.server.common.BuildInfo;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.UserDialogMapper;
import com.voolex.chat.server.mapper.UserEntityMapper;
import com.voolex.chat.server.repository.UserDialogsRepository;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.UserDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
public class InitMessageCreatorServiceDefault implements InitMessageCreatorService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private UserDialogService userDialogService;

    @Autowired
    private BuildInfo buildInfo;

    @Override
    //@Transactional
    public InitMessage createInitMessage(UserEntity userEntity) {
        InitMessage initMessage = InitMessage.builder()
                .message("Init message")
                .serverVersion(buildInfo.getVersion())
                .subscriptionInfo(new SubscriptionInfo(
                        createPrivateMessagesDestination(userEntity),
                        "Private messages destination")
                )
                .userEntityDTO(userEntityMapper.toDTO(userEntity))
                .userDialogs(userDialogService.findByUserEntity(userEntity))
                .build();

        userEntity.getUserDialogs().forEach(System.out::println);
//        var dialogs = userDialogsRepository.findByUserEntity(userEntity);
////        dialogs.ifPresent(d -> d.forEach(System.out::println));
//        dialogs.forEach(System.out::println);
        return initMessage;
    }

    private String createPrivateMessagesDestination(UserEntity userEntity) {
        return "/user/%s/private/messages".formatted(userEntity.getUsername());
    }
}
