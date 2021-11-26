package com.voolex.chat.server.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_dialogs")
@ToString
public class UserDialogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Exclude
    @Getter(value = AccessLevel.PRIVATE)
    private Long id;
//
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "user_id")
////    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<UserEntity> userId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "with_user_id", referencedColumnName = "id")
    private UserEntity withUserEntity;
//
//    @OneToMany
//    @JoinColumn(name = "with_user_id", referencedColumnName = "id")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<UserEntity> withUserId;
}
