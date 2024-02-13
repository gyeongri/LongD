package io.openvidu.recording.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("https://i10d206.p.ssafy.io","http://i10d206.p.ssafy.io:3001", "http://192.168.100.102:5173", "http://192.168.100.103:5173", "http://192.168.100.188:5173", "http://192.168.0.2:5173", "http://192.168.0.39:5173/", "http://192.168.100.116:5173/","http://192.168.236.200:5173/","http://192.168.236.188:5173/","http://192.168.0.6:5173/","http://localhost:3001","http://192.168.100.116:3001/"));

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
