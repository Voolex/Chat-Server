package com.voolex.chat.server.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_dialogs")
@IdClass(UserDialogsPrimaryKey.class)
public class UserDialogs {

    @Id
    @Column(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private long userId;

    @Id
    @Column(name = "with_user_id")
    private long withUserId;
}
