package com.voolex.chat.server.common;


import com.voolex.chat.common.dto.messages.user.UserMessage;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.model.ChatUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Класс содержит информацию, необходимую для обработки приватных сообщений по цепочке
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PrivateMessageHandlerInfo {
    private final ChatUser currentChatUser;
    private final UserMessage userMessage;

    private PrivateMessage privateMessage;
}
