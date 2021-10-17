package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.dto.common.UserEntityDTO;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;

@Service
public class UserEntityMapperDefault implements UserEntityMapper{
    @Override
    public UserEntityDTO toDTO(UserEntity entity) {
        return new UserEntityDTO(entity.getId(), entity.getUsername());
    }
}
