package com.voolex.chat.server.repository;

import com.voolex.chat.server.entity.PrivateMessageNotification;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateMessageNotificationRepository extends JpaRepository<PrivateMessageNotification, String> {

}
