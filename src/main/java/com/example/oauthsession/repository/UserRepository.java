package com.example.oauthsession.repository;

import com.example.oauthsession.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
//    UserEntity findByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    UserEntity findBySocialUsername(String socialUsername);

    Boolean existsByEmail(String email);
}
