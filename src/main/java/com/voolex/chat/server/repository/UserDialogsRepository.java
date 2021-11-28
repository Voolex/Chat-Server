package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDialogsRepository extends JpaRepository<UserDialog, Long> {
    List<UserDialog> findByUserEntity(UserEntity userEntity);
}
