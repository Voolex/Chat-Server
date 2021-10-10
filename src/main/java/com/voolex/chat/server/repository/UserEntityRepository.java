package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findById(Long id);
    public Optional<UserEntity> findByUsername(String username);
}
