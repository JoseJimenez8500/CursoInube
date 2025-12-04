package com.inube.inube_gateway_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

// Clase de configuración que define beans relacionados con WebClient.
// WebClient es el cliente HTTP reactivo de Spring (alternativa moderna a RestTemplate).
public class WebClientConfig {

    // Crea un bean de tipo WebClient.Builder para inyectarlo donde se necesite.
    @Bean
// Permite que este WebClient use balanceo de carga entre múltiples instancias
// de un microservicio registrado en el Discovery Server (Eureka, Consul, etc.).
// Esto significa que si llamas a http://NOMBRE-DEL-SERVICIO, Spring buscará
// automáticamente sus instancias y distribuirá las peticiones.
    @LoadBalanced
    public WebClient.Builder builder() {
// Devuelve un builder limpio al que luego se le pueden agregar filtros, // headers, timeouts, autenticación, etc.
        return WebClient.builder();
    }
}