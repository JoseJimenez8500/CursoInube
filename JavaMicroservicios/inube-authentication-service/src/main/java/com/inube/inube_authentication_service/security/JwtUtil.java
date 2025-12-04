package com.inube.inube_authentication_service.security;

import com.inube.inube_authentication_service.entity.InuUsuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date; import java.util.HashMap;
import java.util.Map;
@Component // Indica que esta clase es un componente gestionado por Spring
@ConfigurationProperties (prefix = "jwt")
// Permite leer propiedades del archivo application.yml o properties con el prefijo "jwt"
// Ejemplo: jwt.secret = asdasdasd123123
public class JwtUtil {
    // Logger para imprimir mensajes en consola cuando ocurre un error
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Setter
    private String secret;
    // La clave secreta usada para firmar y validar los tokens (viene del application.yml)

    private Key key;
    // Representación del secret convertida a una clave criptográfica

    @jakarta.annotation.PostConstruct
    public void init() {
        // Este metodo se ejecuta automaticamente despues de construir el bean
        // Convierte el "secret" en una llave válida para firmar tokens HMAC
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Obtiene todos los "claims (datos) del token JWT
    public Claims getAllClaimsFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts
                .parser() // Inicia el parser de JWT
                .verifyWith(secretKey) // Verifica el token con la clave secreta
                .build()
                .parseSignedClaims(token) // Parsea el token firmado
                .getPayload(); // Obtiene el cuerpo del token (claims)
    }

    // Verifica si un token ya expiró
    private Boolean isTokenExpired(String token) {
        try {
            // Compara la fecha de expiración con la fecha actual
            return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            logger.error(e.getMessage()); // Si hay error (token inválido), lo registra
            return true; // Considera el token como expirado/inválido
        }
    }

    // Regresa true si el token es inválido (expirado)
    public Boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

    // Genera un token JWT para un usuario
    public String generate(InuUsuario inuUsuario) {
        Map<String, Object> claims = new HashMap<>();
        // Se agregan datos personalizados dentro del token (claims)
        claims.put("uuidUsuario", inuUsuario.getUuidUsuario());
        return doGenerateToken(claims, inuUsuario);
    }

    // Construye y firma el JWT
    private String doGenerateToken(Map<String, Object> claims, InuUsuario inuUsuario) {

        long expirationTimeLong = 12 * 60 * 60 * 1000;
        // Tiempo de expiración: 12 horas en milisegundos

        final Date createdDate = new Date(); // Momento en que se generó
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);
        return Jwts.builder()
                .claims(claims) // Datos adicionales dentro del token
                .subject(inuUsuario.toString())
                // El "subject identifica al usuario. Podrías usar username mejor que toString()
                .issuedAt(createdDate) // Fecha de creación
                .expiration(expirationDate) // Fecha limite
                .signWith(key) // Firma el token usando la clave secreta
                .compact(); // Compacta todo en un string (el token final)
    }
}

