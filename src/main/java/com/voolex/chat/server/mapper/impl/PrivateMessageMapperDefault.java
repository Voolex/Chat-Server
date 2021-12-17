package com.voolex.chat.server.mapper.impl;

import com.voolex.chat.common.v2.dto.common.PrivateMessageDTO;
import com.voolex.chat.server.entity.PrivateMessage;
import com.voolex.chat.server.mapper.MapperDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class PrivateMessageMapperDefault implements MapperDTO<PrivateMessage, PrivateMessageDTO> {
//
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public PrivateMessageDTO toDTO(PrivateMessage privateMessage) {

//        Session session = entityManager.unwrap(Session.class);
////        privateMessage = (PrivateMessage) session.merge(privateMessage);
//
////        session.update(privateMessage);
        return new PrivateMessageDTO(
                privateMessage.getSender().getId(),
                privateMessage.getRecipient().getId(),
                privateMessage.getPayload(),
                privateMessage.getPayloadType(),
                privateMessage.getDateTime());
    }
}
