package com.voolex.chat.server.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность пользовательского аккаунта в БД
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private boolean isLocked;
}