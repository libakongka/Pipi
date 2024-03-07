package com.example.oauthsession.entity;

import com.example.oauthsession.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String role;
    private String socialUsername;
    private String address;

    @CreatedDate
    private LocalDateTime signupDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public static UserEntity toUserEntity(UserDTO userDTO){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.setName(userDTO.getName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setSocialUsername(userDTO.getSocialUsername());
        userEntity.setAddress(userDTO.getAddress());
        if(userDTO.getUsername().equals("admin")){
            userEntity.setRole("ROLE_ADMIN");
        }else{
            userEntity.setRole("ROLE_USER");
        }

        return userEntity;
    }
}
