package com.example.oauthsession.service;

import com.example.oauthsession.dto.UserDTO;
import com.example.oauthsession.entity.UserEntity;
import com.example.oauthsession.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }


    @Transactional
    public void signup(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);

        userRepository.save(userEntity);
        System.out.println("@#@#@#@#@#@#@# 가입일 ================"+userEntity.getSignupDate());
    }

    /*이메일 중복 검사*/
    public Integer checkEmailDuplicate(String email) {
        boolean existemail = userRepository.existsByEmail(email);
        int result;
        if(existemail){
            result = 1;
        }else {
            result = 0;
        }
        return result;
    }
}
