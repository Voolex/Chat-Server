package com.voolex.chat.server.service.entityservice.impl;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import com.voolex.chat.server.repository.PrivateMessageRepository;
import com.voolex.chat.server.repository.UserEntityRepository;
import com.voolex.chat.server.service.entityservice.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

//    @Override
//    @Transactional
//    public List<PrivateMessage> findAllBySender(UserEntity sender) {
//        var pages = privateMessageRepository.findAllBySender(sender, PageRequest.of(0, 50, Sort.by("dateTime")));
//        return pages.getContent();
//    }
//
//    @Override
//    @Transactional
//    public List<PrivateMessage> findAllBySender(long userId) {
//        // TODO
//        return null;
//    }

    @Override
    public List<PrivateMessage> findAllBySender(UserEntity sender, int page, int size) {
        Page<PrivateMessage> pages = privateMessageRepository.findAllBySender(
                sender,
                PageRequest.of(page, size, Sort.by("dateTime"))
        );

        return pages.getContent();
    }

    @Override
    public List<PrivateMessage> findAllBySender(long senderId, int page, int size) {
        UserEntity sender = userEntityRepository.getById(senderId);

        Page<PrivateMessage> pages = privateMessageRepository.findAllBySender(
                sender,
                PageRequest.of(page, size, Sort.by("dateTime"))
        );

        return pages.getContent();
    }

    @Override
    public List<PrivateMessage> findAllBySender(UserEntity sender, int page) {
        Page<PrivateMessage> pages = privateMessageRepository.findAllBySender(
                sender,
                PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("dateTime"))
        );

        return pages.getContent();
    }

    @Override
    public List<PrivateMessage> findAllBySender(long senderId, int page) {
        UserEntity sender = userEntityRepository.getById(senderId);
        Page<PrivateMessage> pages = privateMessageRepository.findAllBySender(
                sender,
                PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("dateTime"))
        );

        return pages.getContent();
    }

    @Override
    @Transactional
    public PrivateMessage save(PrivateMessage privateMessage) {
        return privateMessageRepository.save(privateMessage);
    }
}
