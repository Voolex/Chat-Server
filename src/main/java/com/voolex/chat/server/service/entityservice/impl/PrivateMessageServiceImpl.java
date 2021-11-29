package com.voolex.chat.server.service.entityservice.impl;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.repository.PrivateMessageRepository;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Override
    @Transactional
    public List<PrivateMessage> findAllBySender(UserEntity sender) {
        // TODO
        return null;
    }

    @Override
    @Transactional
    public List<PrivateMessage> findAllBySender(long userId) {
        // TODO
        return null;
    }
}
