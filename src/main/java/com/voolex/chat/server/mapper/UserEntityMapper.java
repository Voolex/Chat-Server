package com.voolex.chat.server.mapper;

import com.voolex.chat.common.dto.common.UserEntityDTO;
import com.voolex.chat.server.entity.UserEntity;

/**
 * Преобразует UserEntity в UserEntityDTO
 */
public interface UserEntityMapper {
    /**
     * Метод преобразует UserEntity в UserEntityDTO
     * @param entity сущность из БД
     * @return DTO
     */
    UserEntityDTO toDTO(UserEntity entity);
}
