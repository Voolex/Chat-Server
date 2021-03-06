package com.voolex.chat.server.entity;

import com.voolex.chat.common.v2.dto.common.MessageType;
import com.voolex.chat.common.v2.dto.common.PayloadType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность приватного сообщения
 */

@Entity
@Table(name = "private_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PrivateMessage {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserEntity sender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private UserEntity recipient;

    @Column(name = "payload")
    private String payload;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "payload_type")
    private PayloadType payloadType;
}
