package com.voolex.chat.server.util;

import java.util.UUID;

/**
 * Класс предоставляет всякие рандомные штуки
 *
 */
public class RandomUtil {

    /**
     * Метод получения уникального UUID
     * @return UUID
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Метод получения уникального UUID по информации о двух ID Entity
     * @param id1 senderId or recipientId
     * @param id2 senderId or recipientId
     * @return UUID
     */
    public static String getRandomTwoEntityUUID(long id1, long id2) {
        return id1 + id2 + getRandomUUID();
    }
}
