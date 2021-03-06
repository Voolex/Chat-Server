package com.voolex.chat.server.model;

import com.voolex.chat.server.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class ChatUser implements UserDetails {

    private final UserEntity userEntity;
    private final Collection<? extends GrantedAuthority> authorities;

    public ChatUser(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        this.userEntity = userEntity;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return userEntity.getId();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userEntity.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ChatUser) {
            return this.getId().equals(((ChatUser) obj).getId());
        }
        return false;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEntity);
    }
}
