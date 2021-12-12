package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.PrivateMessageNotification;
import com.voolex.chat.server.entity.UserEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateMessageNotificationRepository extends JpaRepository<PrivateMessageNotification, String> {

    List<PrivateMessageNotification> findAllBySenderAndRecipientAndIsReaded(UserEntity sender, UserEntity recipient, boolean isReaded);
    List<PrivateMessageNotification> findAllByRecipientAndIsReaded(UserEntity recipient, boolean isReaded);
}
