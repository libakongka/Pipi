package com.example.oauthsession.dto;

import com.example.oauthsession.entity.UserDetailEntity;
import com.example.oauthsession.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserDetailEntity userDetailEntity;
    private UserEntity userEntity;

    public CustomUserDetails(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
//                System.out.println("@# CustomUserDetails-> Collectn / getAuthority getRole정보 =======>"+userEntity.getRole());
                return userEntity.getRole();
            }
        });
//        return collection;
        return collection;
    }

    @Override
    public String getPassword() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getPassword정보 =======>"+userDetailEntity.getPassword());
        return userDetailEntity.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getUserId정보 =======>"+userDetailEntity.getUsername());
        return userDetailEntity.getUsername();
    }

    //계정 만료여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 유효기간
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return userEntity.getName();
    }
}
