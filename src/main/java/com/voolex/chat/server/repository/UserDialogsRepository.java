package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.UserDialogs;
import com.voolex.chat.server.entity.UserDialogsPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDialogsRepository extends JpaRepository<UserDialogs, UserDialogsPrimaryKey> {
    Optional<UserDialogs> findByUserId(long userId);
}
