package com.voolex.chat.server.service.entityservice;

import com.voolex.chat.server.entity.UserEntity;

import java.util.Optional;

public interface UserEntityService {
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUsername(String username);
}
