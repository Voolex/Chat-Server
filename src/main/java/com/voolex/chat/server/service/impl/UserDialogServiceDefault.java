package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.UserDialogMapper;
import com.voolex.chat.server.service.UserDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDialogServiceDefault implements UserDialogService {

    @Autowired
    private UserDialogMapper userDialogMapper;

    /**
     * Метод позволяет получить список диалогов в виде DTO
     * @param userEntity пользователь, диалоги которого необходимо получить
     * @return список диалогов пользователя
     */
    @Override
    @Transactional
    public List<UserDialogDTO> findByUserEntity(UserEntity userEntity) {
        return userEntity.getUserDialogs().stream()
                .map(
                        entity -> userDialogMapper.toDTO(entity)
                )
                .collect(Collectors.toList());
    }
}
