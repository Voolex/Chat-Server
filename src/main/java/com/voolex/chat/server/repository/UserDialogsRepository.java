package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.UserDialog;
import com.voolex.chat.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDialogsRepository extends JpaRepository<UserDialog, Long> {
    List<UserDialog> findByUserEntity(UserEntity userEntity);

    // TODO Доработать запрос
    /**
     * Метод проверяет, существует ли диалог между пользователями
     * @param userId пользователь инициатор сообщения
     * @param withUserId пользователь получатель сообщения
     * @return 1 в случае если диалог существует, 0 в протвном случае
     */
    @Query(value = """
        SELECT EXISTS
        (SELECT 1 FROM users_dialogs WHERE 
        (user_id = :userId and with_user_id = :withUserId) 
        or (user_id = :withUserId and with_user_id = :userId) 
        LIMIT 1)
        """, nativeQuery = true)
    int existByUserIdAndWithUserId(@Param("userId") long userId, @Param("withUserId") long withUserId);
}
