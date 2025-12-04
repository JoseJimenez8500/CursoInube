package com.inube.inube_gateway_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

// Marca esta clase como un componente de Spring para que pueda inyectarse donde se necesite
@Component
// Permite leer propiedades del archivo application.yml con el prefijo "jwt"
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    // Logger para imprimir errores o mensajes en consola
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class); // 1 usage
    // Se obtiene el "secret" desde application.yml (Spring lo inyecta)
    @Setter
    private String secret;
    // Llave final que se construye a partir del secret (HMAC)
    private Key key; // 1 usage
    // Se ejecuta automáticamente cuando se crea la clase.
    // Aquí generamos la llave usando el secret.
    @jakarta.annotation.PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    // Obtiene TODOS los claims (datos) guardados dentro del token.
    public Claims getAllClaimsFromToken(String token) { // 2 usages
        // Crea una llave secreta basada en el secret configurado
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        // Construye un parser (lector) para el token usando la llave secreta
        // Luego extrae los claims (payload)
        return Jwts
                .parser()
                .verifyWith(secretKey) // Verifica la firma del token
                .build()
                .parseSignedClaims(token)
                .getPayload(); // Retorna los claims contenidos en el token
    }

    // Revisa si el token ya expiró
    private Boolean isTokenExpired(String token) {
        try{
    // Compara la fecha de expiración del token con la fecha actual
                return this.getAllClaimsFromToken(token)
                        .getExpiration ()
                        .before (new Date());
            }catch (Exception e) {
    // Si hubo un error al intentar leer el token, se considera expirado/inválido Logger.error(e.getMessage());
                return true;
            }
        }
    // Método público para verificar si un token es inválido
    public Boolean isInvalid (String token) {
    return this.isTokenExpired (token);
    }
}