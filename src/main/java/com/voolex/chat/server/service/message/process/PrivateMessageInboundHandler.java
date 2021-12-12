package com.voolex.chat.server.service.message.process;

import com.voolex.chat.server.common.PrivateMessageHandlerInfo;
import com.voolex.chat.server.service.MessageHandler;
import org.springframework.core.Ordered;

/**
 * Интерфейс обработчика входящих сообщений
 * Ordered имплементируется для задания очередности обработчиков
 * В случае неуспешной обработки выбрасывается исключение и обработка приостанавливается
 */
public interface PrivateMessageInboundHandler extends MessageHandler<PrivateMessageHandlerInfo>, Ordered, Comparable<PrivateMessageInboundHandler> {

    @Override
    default int compareTo(PrivateMessageInboundHandler o) {
        return this.getOrder()-o.getOrder();
    }

    /**
     * Включен ли данный обработчик. По умолчанию true. Для выключения установить в false
     * @return включен ли обработчик
     */
    default boolean enabled() {
        return true;
    }

}
