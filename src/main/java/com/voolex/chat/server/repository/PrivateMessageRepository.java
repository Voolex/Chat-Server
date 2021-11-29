package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends PagingAndSortingRepository<PrivateMessage, String> {
    List<PrivateMessage> findAllBySender(UserEntity sender);
}
