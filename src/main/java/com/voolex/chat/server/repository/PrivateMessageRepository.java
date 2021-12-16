package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivateMessageRepository extends PagingAndSortingRepository<PrivateMessage, String> {

    Page<PrivateMessage> findBySender(UserEntity userEntity, Pageable pageable);
    Page<PrivateMessage> findByRecipient(UserEntity recipient, Pageable pageable);

    Page<PrivateMessage> findBySenderAndRecipient(UserEntity sender, UserEntity recipient, Pageable pageable);

    Optional<PrivateMessage> findById(String id);
}
