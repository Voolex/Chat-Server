package com.voolex.chat.server.common;


import com.voolex.chat.common.v2.dto.messages.AbstractPrivateMessage;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.model.ChatUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

/**
 * Класс содержит информацию, необходимую для обработки приватных сообщений по цепочке
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PrivateMessageHandlerInfo {
    private final ChatUser currentChatUser;
    private final AbstractPrivateMessage userMessage;

    private Optional<PrivateMessage> privateMessage = Optional.empty();
    private Optional<PrivateMessageNotification> privateMessageNotification = Optional.empty();
}
