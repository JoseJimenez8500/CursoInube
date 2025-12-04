package com.inube.inube_gateway_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inube.inube_gateway_service.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

// Marca esta clase como componente de Spring para que sea detectada automáticamente
@Component
// Esta clase es un filtro personalizado para Spring Cloud Gateway.
// Extiende AbstractGatewayFilterFactory para poder usarla en el archivo de configuración
// y aplicarla a rutas del Gateway.
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    // Utilidad para validar y extraer datos del JWT
    private final JwtUtil jwtUtil;
    // ObjectMapper para generar respuestas JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // El constructor recibe JwtUtil (inyección de dependencias)
    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class); // Indica a la fábrica qué config usará this.jwtUtil = jwtUtil;
        this.jwtUtil = jwtUtil;
    }

    // Metodo principall donde se crea el filtro
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Obtiene los headers de la petición entrante
            HttpHeaders headers = exchange.getRequest().getHeaders();

            // Validación 1: ¿Existe el header Authorization?
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Authorization header missing");
            }

            // Obtiene el valor del header Authorization
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            // Validación 2: ¿El header empieza con "Bearer *?
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Authorization header missing");
            }


            // Extrae el token quitando el prefijo "Bearer"
            String token = authHeader.substring( 7);
            try {
                // Validación 3: ¿El token es inválido o expiró?
                if (jwtUtil.isInvalid (token)) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED, "Invalid JWT token");
                }

                // Extrae los claims (datos) del JWT
                Claims claims = jwtUtil.getAllClaimsFromToken (token);

                // Obtiene el dato "uuidUsuario del token
                String uuidUsuario = claims.get("uuidUsuario", String.class);

                // Crea una nueva request añadiendo un header personalizado
                // Esto permite que otros microservicios conozcan al usuario.
                ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header("uuidUsuario", uuidUsuario)
                .build();

                // Reemplaza la request original con la modificada
                ServerWebExchange mutatedExchange = exchange.mutate()
                .request(mutatedRequest)
                .build();
                // Continua el flujo de filtros con la request modificada
                return chain. filter (mutatedExchange);
            } catch (JwtException e) {
                //Error cuando el token viene mal formado o firmado incorrectamente
                return onError(exchange, HttpStatus.UNAUTHORIZED, "Jwt validation error");
            } catch (Exception e) {
                //Error cuando el token viene mal formado o firmado incorrectamente
                return onError(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Authentication Error");
            }
        };
    }

    // Método para responder con JSON cuando ocurre un error de autenticación
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {

        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(status);                        // Código HTTP
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // Cuerpo JSON enviado al cliente
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", message);
        body.put("error", status.name());
        try {
            // Convierte el Map a JSON y lo envía
            byte[] bytes = objectMapper.writeValueAsBytes(body);
            return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
        } catch (Exception e) {
            log.error("Error serializing JSON error response", e);
            return response.setComplete();
        }
    }
        // Clase vacía usada solo para que el filtro pueda declararse en el Gateway
        public static class Config {
    }
}