package com.voolex.chat.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность пользовательского аккаунта в БД
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @ToString.Exclude
    private String password;

    @Column(name = "locked")
    private boolean isLocked;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserDialog> userDialogs;
}
