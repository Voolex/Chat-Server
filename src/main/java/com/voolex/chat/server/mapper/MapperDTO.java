package com.voolex.chat.server.mapper;

/**
 * Базовый интерфейс для маппера DTO
 * @param <T> исходная сущность
 * @param <R> возвращаемая сущность
 */
public interface MapperDTO<T, R> {

    R toDTO(T t);
}
