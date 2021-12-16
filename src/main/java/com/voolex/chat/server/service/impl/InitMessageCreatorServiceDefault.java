package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.v2.dto.common.MessageType;
import com.voolex.chat.common.v2.dto.common.NotificationMessageDTO;
import com.voolex.chat.common.v2.dto.common.SubscriptionInfo;
import com.voolex.chat.common.v2.dto.messages.InitMessage;
import com.voolex.chat.server.common.BuildInfo;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.impl.PrivateMessageNotificationMapperDefault;
import com.voolex.chat.server.mapper.impl.UserEntityMapperDefault;
import com.voolex.chat.server.service.InitMessageCreatorService;
import com.voolex.chat.server.service.entityservice.PrivateMessageNotificationService;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import com.voolex.chat.server.service.entityservice.UserDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class InitMessageCreatorServiceDefault implements InitMessageCreatorService {

    @Autowired
    private UserEntityMapperDefault userEntityMapper;

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private PrivateMessageNotificationMapperDefault privateMessageNotificationMapper;

    @Autowired
    private UserDialogService userDialogService;

    @Autowired
    private PrivateMessageNotificationService privateMessageNotificationService;

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
                .notifications(getNotifications(userEntity))
//                .notifications(privateMessageNotificationService.findAllNewNotifications(userEntity).stream()
//                        .map(
//                                privateMessageNotification ->
//                                        privateMessageNotificationMapperDefault.toDTO(privateMessageNotification))
//                        .collect(Collectors.toList())
//                )
                .build();

        userDialogService.findByUserEntity(userEntity).forEach(System.out::println);

//        privateMessageService.findAllBySender(userEntity).forEach(System.out::println);
        var array1 = privateMessageService.findBySender(8, 0);
        array1.forEach(System.out::println);
        System.out.println("________________________________");
        var array2 = privateMessageService.findBySender(8, 1);
        array2.forEach(System.out::println);

        return initMessage;
    }

    private List<NotificationMessageDTO> getNotifications(UserEntity userEntity) {
        var groupingMap = privateMessageNotificationService.
                findAllNewNotifications(userEntity).stream()
                .collect(Collectors.groupingBy(PrivateMessageNotification::getSender));
        return groupingMap.values().stream()
                .map(value -> privateMessageNotificationMapper.toDTO(value)).collect(Collectors.toList());
    }

    private Iterable<String> getDestinations(UserEntity userEntity) {
        return Arrays.stream(MessageType.values())
                .map(messageType -> "/user/" + userEntity.getUsername() + messageType.getDestination())
                .collect(Collectors.toList());
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
