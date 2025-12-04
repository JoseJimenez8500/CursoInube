package com.inube.inube_authentication_service.controller;

import com.inube.inube_authentication_service.dto.LoginDto;
import com.inube.inube_authentication_service.dto.ResponseDto;
import com.inube.inube_authentication_service.dto.TokenDto;
import com.inube.inube_authentication_service.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inube/auth")
@AllArgsConstructor
public class AuthenticationController {
// Servicio encargado de la lógica de autenticación: login, validación de token, etc.
    private final AuthenticationService authenticationService;
    /**
     * POST /api/inu/auth/login
     * Endpoint para iniciar sesión.
     * Recibe un LoginDto con:
     *
     *
     nombreUsuario passwordUsuario
     * Si las credenciales son correctas, retorna un ResponseDto con:
     }
     *
     - datos del usuario
     *
     - token JWT generado
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login (@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok (authenticationService.login(loginDto));
    }
    /**
     * POST /api/inu/auth/validate
     * Valida un token JWT enviado desde el cliente.
     wwwwww
     * Recibe un TokenDto con:
     * - token
     * Retorna un ResponseDto indicando si:
     * - el token es válido
     * - el token es inválido o expirado
     */
    @PostMapping("/validate")
    public ResponseEntity<ResponseDto> validate (@RequestBody @Valid TokenDto tokenDto){
        return ResponseEntity.ok(authenticationService.validate(tokenDto.token()));
    }
}