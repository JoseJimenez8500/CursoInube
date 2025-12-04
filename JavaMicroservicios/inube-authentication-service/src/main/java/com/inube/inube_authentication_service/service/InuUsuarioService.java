package com.inube.inube_authentication_service.service;

import com.inube.inube_authentication_service.dto.ResponseDto;
import com.inube.inube_authentication_service.entity.InuUsuario;
import com.inube.inube_authentication_service.repository.InuUsuarioRepository;
import com.inube.inube_authentication_service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//clase de servicio encargada de manejar la logica de negocio para la entidad InuUsuario
//AllArgsConstructor genera el contructor con todos los atributos reueridos
@Service
@AllArgsConstructor
public class InuUsuarioService {

    private final InuUsuarioRepository inuUsuarioRepository; // Acceso a la tabla de usuarios
    private final PasswordEncoder passwordEncoder; // Para encriptar contraseñas

    //Metodo: obtener todos los usuarios
    @Transactional(readOnly= true)
    public ResponseDto findAll(){
        // Recupera todos los registros de la tabla INU_USUARIO
        List<InuUsuario> inuUsuarioList = inuUsuarioRepository.findAll();
        return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY, null, inuUsuarioList);
    }

    //Metodo: buscar usuario por UUID
    @Transactional(readOnly= true)
    public ResponseDto findById(UUID uuidUsuario){
        return inuUsuarioRepository.findById(uuidUsuario)
                //si existe devuelve el usuario
                .map(inuUsuario -> new ResponseDto(Util.OKSUCCESS, Util.OKFOUND, null, inuUsuario))
                //si no existe devuelve notFoundResponse
                .orElseGet(this::notFoundResponse);
    }

    //metodo crear nuevo usuario
    @Transactional
    public ResponseDto save(InuUsuario inuUsuario, UUID usuarioCreacion){
        //validar que el nombre de usuario no este repetido
        if(inuUsuarioRepository.findByNombreUsuario(inuUsuario.getNombreUsuario()).isPresent()){
            return new ResponseDto(Util.ERRORSUCCESS, Util.NOTFOUND, Util.NOTFOUND, null);
        }

        //encriptar la contraseña antes de guardar
        String passwordEncoded = passwordEncoder.encode(inuUsuario.getPasswordUsuario());

        //completar datos del usuario antes de guardar
        inuUsuario.setPasswordUsuario(passwordEncoded)
                .setEstatusRegistro(Util.OKCODE)
                .setFechaCreacion(LocalDateTime.now())
                .setUuidUsuario(usuarioCreacion);

        //Guardar usuario en la BD
        inuUsuarioRepository.save(inuUsuario);
        return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY, null, inuUsuario);

    }

    //Metodo actualizar usuario por la UUID
    @Transactional
    public ResponseDto update(InuUsuario inuUsuarioUpdated, UUID uuidUsuario, UUID usuarioModificacion){
        return inuUsuarioRepository.findById(uuidUsuario)
                .map(inuUsuarioExistente -> {
                    //Validar que el nuevo nombre de usuario no exista en otra cuenta
                    InuUsuario usuarioDuplicado = inuUsuarioRepository.findByNombreUsuario(inuUsuarioUpdated.getNombreUsuario())
                            .filter(u -> !u.getUuidUsuario().equals(inuUsuarioExistente.getUuidUsuario()))
                            .orElse(null);

                    if(usuarioDuplicado != null){
                        //error nombre de usuario duplicado
                        return new ResponseDto(Util.ERRORSUCCESS, Util.NOTFOUND, Util.NOTFOUND, null);
                    }

                    //Actualiza campos permitidos
                    inuUsuarioExistente.setNombreUsuario(inuUsuarioUpdated.getNombreUsuario())
                            .setUuidPersona(inuUsuarioUpdated.getUuidPersona())
                            .setUuidTipoPerfil(inuUsuarioUpdated.getUuidTipoPerfil())
                            .setFechaModificacion(LocalDateTime.now())
                            .setUuidUsuarioModificacion(usuarioModificacion);

                    //guarda cambios
                    inuUsuarioRepository.save(inuUsuarioExistente);

                    return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY, null, inuUsuarioExistente);
                })
                .orElseGet(this::notFoundResponse);
    }

    //Metodo buscar usuario por UUID de persona
    @Transactional(readOnly = true)
    public ResponseDto findByUuidPersona(UUID uuidPersona){
        return inuUsuarioRepository.findByUuidPersona(uuidPersona)
                .map(abaUsuario -> new ResponseDto(Util.OKSUCCESS, Util.OKFOUND, null, abaUsuario))
                .orElseGet(this::notFoundResponse);
    }

    // MÉetodo eliminacion logica por UUID de persona
        @Transactional
        public ResponseDto deleteByUuidPersona (UUID uuidPersona, UUID uuidUsuario){
        return inuUsuarioRepository.findByUuidPersona(uuidPersona)
                .map(abaUsuario -> {
                    // Eliminación lógica: no se borra, solo se cambia el estatus
                    abaUsuario.setEstatusRegistro (Util.DELETECODE)
                            .setFechaModificacion (LocalDateTime.now())
                            .setUuidUsuarioModificacion (uuidUsuario);
                    inuUsuarioRepository.save(abaUsuario);
                    return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY, null, null); })
                    .orElseGet(this::notFoundResponse);
        }


        // Metodo eliminacion logica por UUID de usuario

        @Transactional
        public ResponseDto deleteById(UUID uuidUsuario, UUID usuarioModificacion) {
            return inuUsuarioRepository.findById(uuidUsuario)
                    .map(inuUsuario -> {
                        inuUsuario.setEstatusRegistro(Util.DELETECODE)
                                .setFechaModificacion(LocalDateTime.now())
                                .setUuidUsuarioModificacion(usuarioModificacion);

                        inuUsuarioRepository.save(inuUsuario);

                        return new ResponseDto(Util.OKSUCCESS, Util.OKQUERY, null, null);
                    })
                    .orElseGet(this::notFoundResponse);
        }

        //respuesta por defecto para registros no encontrados
    private ResponseDto notFoundResponse(){
        return new ResponseDto(Util.ERRORSUCCESS, Util.NOTFOUND, Util.NOTFOUND, null);
    }
}
