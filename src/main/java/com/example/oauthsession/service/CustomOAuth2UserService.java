package com.example.oauthsession.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.oauthsession.dto.CustomOAuth2User;
import com.example.oauthsession.dto.GoogleReponse;
import com.example.oauthsession.dto.NaverResponse;
import com.example.oauthsession.dto.OAuth2Response;
import com.example.oauthsession.entity.UserEntity;
import com.example.oauthsession.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService OAuth2UserService의 구현체

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //어떤 인증 provide인지 담을 변수
        OAuth2Response oAuth2Response = null;

        if(registrationId.equals("naver")){
            //naver json data : resultcode=00, message=success, response={id=123123123, name=pipi}
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if(registrationId.equals("google")){
            //google json data : resultcode=00, message=success, id=123123123, name=pipi
            oAuth2Response = new GoogleReponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        //구현
        String socialUsername = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findBySocialUsername(socialUsername);
        String role = null;
        if(existData==null){
            UserEntity userEntity = new UserEntity();
            userEntity.setSocialUsername(socialUsername);
            userEntity.setName(oAuth2Response.getName());
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);
        }else{ //처음 로그인하는 경우가 아닐때 update
            role = existData.getRole();
            existData.setEmail(oAuth2Response.getEmail());

            userRepository.save(existData);
        }

        return new CustomOAuth2User(oAuth2Response,role);
    }

}
