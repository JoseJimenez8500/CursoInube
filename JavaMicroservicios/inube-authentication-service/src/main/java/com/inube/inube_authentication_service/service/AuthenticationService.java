package com.inube.inube_authentication_service.service;

import com.inube.inube_authentication_service.dto.LoginDto;
import com.inube.inube_authentication_service.dto.ResponseDto;
import com.inube.inube_authentication_service.dto.TokenDto;
import com.inube.inube_authentication_service.entity.InuUsuario;
import com.inube.inube_authentication_service.entity.ViwLogin;
import com.inube.inube_authentication_service.entity.ViwUsuarioSucursal;
import com.inube.inube_authentication_service.repository.InuUsuarioRepository;
import com.inube.inube_authentication_service.repository.ViwLoginRepository;
import com.inube.inube_authentication_service.repository.ViwUsuarioSucursalRepository;
import com.inube.inube_authentication_service.security.JwtUtil;
import com.inube.inube_authentication_service.util.Util;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
// Marca la clase como un servicio de Spring.
// @AllArgsConstructor genera un constructor con todos los atributos inyectados automáticamente.
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    // Repositorio para usuarios (tabla)
    private final InuUsuarioRepository inuUsuarioRepository;

    // Para comparar contraseñas en texto plano contra la contraseña encriptada en BD
    private final PasswordEncoder passwordEncoder;

    // Para generar y validar tokens JWT
    private final JwtUtil jwtUtil;

    // Vista que contiene información necesaria para el login
    private final ViwLoginRepository viwLoginRepository;

    // Vista que contiene las sucursales asignadas a un usuario
    private final ViwUsuarioSucursalRepository viwUsuarioSucursalRepository;

    @Transactional(readOnly = true)
    // Indica que la operación es solo de lectura y participa en una transacción.
    public ResponseDto login(LoginDto loginDto) {
        // 1. Buscar usuario por su nombre de usuario
        Optional<InuUsuario> inuUsuarioOptional =
                inuUsuarioRepository.findByNombreUsuario(loginDto.nombreUsuario());

        if (inuUsuarioOptional.isEmpty()) {
            // Log de error si no existe el usuario
            logger.error("{}: {}", Util.LOGINFAIL, loginDto.nombreUsuario());
            return new ResponseDto(Util.ERRORSUCCESS, Util.NOTUSER, Util.NOTFOUND, null);
        }

        InuUsuario inuUsuario = inuUsuarioOptional.get();

        // 2. Validar contraseña
        if (!passwordEncoder.matches(loginDto.passwordUsuario(), inuUsuario.getPasswordUsuario())) {
            logger.error("{}: {}", Util.LOGINFAIL, loginDto.nombreUsuario());
            return new ResponseDto(Util.ERRORSUCCESS, Util.NOTUSER, Util.NOTFOUND, null);
        }

        // 3. Consultar la vista de login

        Optional<ViwLogin> viwLoginOptional =
                viwLoginRepository.findByUsuario(inuUsuario.getNombreUsuario());

        if (viwLoginOptional.isEmpty()){
            logger.error("{}: {}", Util.LOGINFAIL, loginDto.nombreUsuario());
            return new ResponseDto(Util.ERRORSUCCESS, Util.NOTUSER, Util.NOTFOUND, null);
        }

        ViwLogin viwLogin = viwLoginOptional.get();

        // 4. Generar token JWT y asignarlo al objeto de la vista TokenDto
        TokenDto tokenDto = new TokenDto (jwtUtil.generate(inuUsuario));
        viwLogin.setToken(tokenDto.token());

        // 5. Consultar las sucursales del usuario (vista)
        List<ViwUsuarioSucursal> viwUsuarioSucursalList =
                viwUsuarioSucursalRepository.findByUuidUsuario (viwLogin.getUuidUsuario());

        // Si la lista está vacía, asigna una lista vacia para evitar nulls
        viwLogin.setSucursales(
                viwUsuarioSucursalList != null && !viwUsuarioSucursalList.isEmpty()
                        ? viwUsuarioSucursalList
                        : List.of()
        );

        // 6. Registro en bitácora e información correcta
                logger.info("{}: {}", Util.LOGINOK, loginDto.nombreUsuario());

        // 7. Respuesta final con datos del login y el token
                return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY,null, viwLogin);
    }


    //Validacion del token JWT
    public ResponseDto validate(String token) {
        //Si el token es invalido o expiro
        if (jwtUtil.isInvalid(token)) {
            return new  ResponseDto(Util.ERRORSUCCESS, Util.TOKENINVALID, Util.NOTFOUND, null);
        }

        //si es valido
        return new ResponseDto(Util.OKSUCCESS, Util.TOKENVALID, Util.SESSIONVALID, new TokenDto(token));
    }
}