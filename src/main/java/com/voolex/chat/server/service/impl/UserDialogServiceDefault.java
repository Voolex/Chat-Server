package com.voolex.chat.server.service.impl;

import com.voolex.chat.common.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.UserDialogMapper;
import com.voolex.chat.server.repository.UserDialogsRepository;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.UserDialogService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDialogServiceDefault implements UserDialogService {

    @Autowired
    private UserDialogMapper userDialogMapper;

    @Autowired
    private UserDialogsRepository userDialogsRepository;

//    @Autowired
//    private EntityManager entityManager;

    /**
     * Метод позволяет получить список диалогов в виде DTO
     * @param userEntity пользователь, диалоги которого необходимо получить
     * @return список диалогов пользователя
     */
    @Override
    @Transactional
    public List<UserDialogDTO> findByUserEntity(UserEntity userEntity) {

//        Session session = entityManager.unwrap(Session.class);
//        session.update(userEntity);

        var userDialogs = userDialogsRepository.findByUserEntity(userEntity);
//        var userDialogs = userEntity.getUserDialogs();
        return userDialogs.stream()
                .map(
                        entity -> userDialogMapper.toDTO(entity)
                )
                .collect(Collectors.toList());
    }
}
