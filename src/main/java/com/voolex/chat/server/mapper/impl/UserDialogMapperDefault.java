package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.mapper.MapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDialogMapperDefault implements MapperDTO<UserDialog, UserDialogDTO> {

    @Autowired
    private UserEntityMapperDefault userEntityMapper;

    @Override
    public UserDialogDTO toDTO(UserDialog userDialog) {
        return new UserDialogDTO(
                userEntityMapper.toDTO(userDialog.getWithUserEntity()),
                userDialog.getLastMessageDateTime()
        );
    }
}
