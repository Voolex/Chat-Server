package com.voolex.chat.server.service;

/**
 * Интервейс для обработки сообщений (любых)
 * @param <T> входной параметр
 */
public interface MessageHandler<T> {
    T handle(T t);
}
