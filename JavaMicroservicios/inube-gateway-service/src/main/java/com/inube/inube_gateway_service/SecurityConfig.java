package com.inube.inube_gateway_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

// Clase de configuración de seguridad para Spring WebFlux (Spring Cloud Gateway usa WebFlux).
@Configuration
public class SecurityConfig {

    // Define la cadena de filtros de seguridad del Gateway.
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                // Configura las reglas de autorización.
                .authorizeExchange(exchanges -> exchanges

                // En este caso, TODAS las rutas del Gateway están permitidas.
                // La validación real se hace en tu AuthenticationFilter.
                .anyExchange().permitAll()
                )
                // Desactiva CSRF porque en API REST no se usa (solo para apps con sesiones y formularios).
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // Habilita CORS para permitir peticiones desde el frontend.
                // Se usa el método corsConfigurationSource que definimos más abajo.
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // Construye y devuelve la configuración final
        return http.build();
    }

    // Configuración de CORS (Cross-Origin Resource Sharing)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        // Objeto que define qué origenes, métodos y headers están permitidos
        CorsConfiguration config = new CorsConfiguration();
        // Solo permite peticiones desde tu frontend Angular
        config.setAllowedOrigins (List.of("http://localhost:4200"));
        // Métodos HTTP permitidos
        config.setAllowedMethods (List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite cualquier header que mande el cliente
        config.setAllowedHeaders (List.of("*"));
        // Permite el uso de cookies, tokens, etc.
        config.setAllowCredentials (true);
        // Registra esta configuración para todas las rutas del Gateway
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
