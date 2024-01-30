package com.longd.longd.user.config;

import com.longd.longd.user.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    CorsConfig corsConfig;
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {

        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());

        http.cors(httpSecurityCorsConfigurer -> corsConfig.corsConfigurationSource());

        http.formLogin(formLogin ->formLogin.loginPage("/")
                );

        http.httpBasic((basic) -> basic.disable());

        http.oauth2Login((oauth2) -> oauth2.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig.userService(customOAuth2UserService)).defaultSuccessUrl("/user/checkregist"));

        http.authorizeHttpRequests((auth) ->
                auth.requestMatchers("/", "/oauth2/**", "/login/**", "/user/customlogout","/user/customlogin", "/user/state", "/user/checkregist").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated());

        http.logout((logoutConfig) -> logoutConfig.logoutUrl("/user/customlogout").logoutSuccessUrl("http://192.168.100.188:5173/"));

        return http.build();
    }

}
