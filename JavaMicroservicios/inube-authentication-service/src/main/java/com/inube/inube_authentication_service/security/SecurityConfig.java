package com.inube.inube_authentication_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)//desactiva CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/inube/auth/**", "/api/inube/user/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}