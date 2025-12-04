package com.inube.inube_authentication_service.controller;

import com.inube.inube_authentication_service.dto.ResponseDto;
import com.inube.inube_authentication_service.entity.InuUsuario;
import com.inube.inube_authentication_service.service.InuUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inube/user")
@AllArgsConstructor
public class InuUsuarioController {
    // Servicio que contiene la lógica de negocio relacionada con usuarios
    private final InuUsuarioService inuUsuarioService;
    /**
     * GET /api/inu/user
     * Obtiene la lista completa de usuarios registrados.
     */
    @GetMapping("")
    public ResponseEntity<ResponseDto> findAll(){
        return ResponseEntity.ok(inuUsuarioService.findAll());
    }
    /**
     * GET /api/inu/user/{uuidUsuario}
     * Busca un usuario por su UUID.
     *
     * @param uuidUsuario UUID del usuario que se quiere consultar.
     */
    @GetMapping("/{uuidUsuario}")
    public ResponseEntity<ResponseDto> findById(@PathVariable("uuidUsuario") UUID uuidUsuario) {
        return ResponseEntity.ok(inuUsuarioService.findById(uuidUsuario));
    }
    /**
     * GET /api/inu/user/persona/{uuidPersona}
     * Obtiene el usuario asociado a un UUID de persona.
     *
     * @param uuidPersona identificador unico de la persona
     */

    @GetMapping("/persona/{uuidPersona}")
    public ResponseEntity<ResponseDto> findByUuidPersona(@PathVariable("uuidPersona") UUID uuidPersona) {
        return ResponseEntity.ok(inuUsuarioService.findByUuidPersona(uuidPersona));
    }

    /**
     * POST /api/inu/user
     * Crea un nuevo usuario.
     *
     * @param inuUsuario Cuerpo del request con los datos del usuario.
     * @param uuidUsuario UUID del usuario que realiza la creación (enviado en el Header).
     */
    @PostMapping("")
    public ResponseEntity<ResponseDto> save(
            @RequestBody InuUsuario inuUsuario,
            @RequestHeader("uuidUsuario") UUID uuidUsuario) {
        return ResponseEntity.ok(inuUsuarioService.save(inuUsuario, uuidUsuario));
    }

    /**
     * PUT /api/inu/user/{uuidUsuario}
     * Actualiza la información de un usuario existente.
     *
     * @param inuUsuario Datos nuevos del usuario.
     * @param uuidUsuario UUID del usuario que se va a actualizar.
     * @param usuarioModificacion UUID del usuario que realiza la modificación (Header).
     */
    @PutMapping("/{uuidUsuario}")
    public ResponseEntity<ResponseDto> update(
            @RequestBody InuUsuario inuUsuario,
            @PathVariable("uuidUsuario") UUID uuidUsuario,
            @RequestHeader("uuidUsuario") UUID usuarioModificacion) {
        return ResponseEntity.ok(inuUsuarioService.update(inuUsuario, uuidUsuario, usuarioModificacion));
    }

    //  Delete /api/inu/user/persona/{uuidPersona}
    // Elimina un usuario usando el UUID uuidPersona
    // @param uuidPersona identificador de la persona
    // @param usuarioModificacion UUID de quien ejecuta la eliminacion

    @DeleteMapping("/persona/{uuidPersona}")
    public ResponseEntity<ResponseDto> deleteByUuidPersona(
            @PathVariable("uuidPersona") UUID uuidPersona,
            @RequestHeader("uuidUsuario") UUID usuarioModificacion)
    {
        return ResponseEntity.ok(inuUsuarioService.deleteByUuidPersona(uuidPersona, usuarioModificacion));
    }
}
