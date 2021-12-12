package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.v2.dto.common.UserEntityDTO;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.MapperDTO;
import org.springframework.stereotype.Service;

@Service
public class UserEntityMapperDefault implements MapperDTO<UserEntity, UserEntityDTO> {

    @Override
    public UserEntityDTO toDTO(UserEntity entity) {
        return new UserEntityDTO(entity.getId(), entity.getUsername());
    }
}
