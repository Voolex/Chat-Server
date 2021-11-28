package com.voolex.chat.server.mapper;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.common.dto.common.UserEntityDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;

public interface UserDialogMapper {
    /**
     * Метод преобразует UserDialog в UserDialogDTO
     * @param userDialog сущность из БД
     * @return DTO
     */
    UserDialogDTO toDTO(UserDialog userDialog);
}
