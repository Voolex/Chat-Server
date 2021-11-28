package com.voolex.chat.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @ToString.Exclude
    private String password;

    @Column(name = "locked")
    private boolean isLocked;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<UserDialog> userDialogs;
}
