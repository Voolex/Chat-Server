package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.mapper.UserDialogMapper;
import com.voolex.chat.server.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDialogMapperDefault implements UserDialogMapper {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public UserDialogDTO toDTO(UserDialog userDialog) {
        return new UserDialogDTO(
                userEntityMapper.toDTO(userDialog.getWithUserEntity()),
                userDialog.getLastMessageDateTime()
        );
    }
}
