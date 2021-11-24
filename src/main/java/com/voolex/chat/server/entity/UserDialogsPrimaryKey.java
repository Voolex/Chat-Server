package com.voolex.chat.server.entity;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDialogsPrimaryKey implements Serializable {

    private long userId;

    private long withUserId;
}
