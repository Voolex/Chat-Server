package com.voolex.chat.server.service.entityservice.impl;

import com.voolex.chat.common.v2.dto.common.UserDialogDTO;
import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.mapper.impl.UserDialogMapperDefault;
import com.voolex.chat.server.repository.UserDialogsRepository;
import com.voolex.chat.server.service.entityservice.UserDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDialogServiceImpl implements UserDialogService {

    @Autowired
    private UserDialogMapperDefault userDialogMapper;
    
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

    @Override
    @Transactional
    public boolean existByUserIdAndWithUserId(long userId, long withUserId) {
        return userDialogsRepository.existByUserIdAndWithUserId(userId, withUserId) > 0;
    }

    @Override
    @Transactional
    public UserDialog save(UserDialog userDialog) {
        return userDialogsRepository.save(userDialog);
    }
}
