package com.example.oauthsession.dto;

import com.example.oauthsession.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String role;
    private String socialUsername;
    private String address;
    private LocalDateTime signupDate;
    private LocalDateTime lastModifiedDate;

    public static UserDTO toUserDTO(UserEntity userEntity){

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setName(userEntity.getName());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setSocialUsername(userEntity.getSocialUsername());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setRole(userEntity.getRole());
        userDTO.setSignupDate(userEntity.getSignupDate());
        userDTO.setLastModifiedDate(userEntity.getLastModifiedDate());


        return userDTO;
    }

}
