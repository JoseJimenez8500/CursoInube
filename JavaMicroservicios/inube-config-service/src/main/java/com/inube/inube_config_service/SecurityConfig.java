package com.inube.inube_config_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// Indica que esta clase es de configuración de Spring
@Configuration

// Habilita la seguridad web en Spring (Spring Security)
@EnableWebSecurity
public class SecurityConfig {
// Define un bean que configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
            // Configura la autorización de las solicitudes HTTP
            // En este caso, permite que cualquier solicitud sea accesible (no requiere autenticación)
            .authorizeHttpRequests (auth -> auth.anyRequest().permitAll())
            // Desactiva la protección CSRF (Cross-Site Request Forgery)
            // Esto se hace a menudo en APIS REST donde no hay sesiones de navegador
            .csrf(AbstractHttpConfigurer::disable)
            // Habilita la autenticación básica HTTP
            // Esto hará que el navegador o Postman soliciten usuario y contraseña
            .httpBasic (Customizer.withDefaults());

        // Construye y devuelve el filtro de seguridad configurado
        return http.build();
    }
    }