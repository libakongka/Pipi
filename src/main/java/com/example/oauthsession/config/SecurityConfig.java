package com.example.oauthsession.config;

import com.example.oauthsession.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //css, img, js 접근 허용
        return (web) ->
                web.ignoring().requestMatchers("/css/**", "/image/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth)->auth
//                                .requestMatchers("/", "/oauth2/**", "/member/signin", "/login", "/login/**").permitAll()
                                .requestMatchers("/cart/order/**", "/cs/inquiry/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/order/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/order/**").hasRole("ADMIN")
                                .requestMatchers("/admin/member/**", "/admin/product/**", "/admin/**").hasRole("ADMIN")
                                .requestMatchers("/member/mypage").hasAnyRole("USER", "ADMIN")
                                //.antMatchers("/h2-console/**").permitAll()
                                .anyRequest().permitAll()
                );




        http
                .csrf((csrf) -> csrf.disable()); //개발환경이라 비활성화시킴

        http
                .formLogin((form) -> form
                        .loginPage("/member/signin")
                        .loginProcessingUrl("/member/signin") // 로그인 처리 URL
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll());//폼로그인방식
        http .logout((logout) -> logout
                .logoutSuccessUrl("/").permitAll());

        http
                .httpBasic((basic) -> basic.disable()); //http 베이직 방식

        http
//                .oauth2Login(Customizer.withDefaults());
                .oauth2Login((oauth2)->oauth2
                        .loginPage("/login")
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));


        return http.build();
    }
}
