package com.longd.longd.user.config;

import com.longd.longd.user.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {

        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable());

        http.formLogin(formLogin ->formLogin.loginPage("/user/customlogin")  // 사용자 정의 로그인 페이지 경로 설정
                                  // 모든 사용자가 로그인 페이지에 접근할 수 있도록 허용
                                .defaultSuccessUrl("/user/login/success", true).permitAll()
                );

        http.httpBasic((basic) -> basic.disable());

        http.oauth2Login((oauth2) -> oauth2.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig.userService(customOAuth2UserService)));

        http.authorizeHttpRequests((auth) ->
                auth.requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        http.logout((logoutConfig) -> logoutConfig.logoutUrl("/user/logout").logoutSuccessUrl("/user/logout/success"));

        return http.build();
    }

}
