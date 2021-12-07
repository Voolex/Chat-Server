package com.voolex.chat.server.service.entityservice;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;

import java.util.List;

public interface UserDialogService {
    List<UserDialogDTO> findByUserEntity(UserEntity userEntity);

    boolean isUserDialogExist(long userId, long withUserId);
}
