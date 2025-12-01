package com.inube.actividades_academicas.service;

import com.inube.actividades_academicas.model.Usuario;
import com.inube.actividades_academicas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //Dependencia del repositorio que maneja la BD
    private final UsuarioRepository usuarioRepository;

    //Inyeccion de dependencias por constructor (buena practica)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Obtiene todos los clientes de la base de datos.
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findEstatusActivo() {
        return usuarioRepository.findByEstatus("Activo");
    }

    public List<Usuario> findEstatusInactivo() {
        return usuarioRepository.findByEstatus("Inactivo");
    }

    //Busca un usuario por ID usando el metodo basico de JpaaRepository
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    //Guarda o actualiza un cliente
    //si el ID es null -> crea uno nuevo
    //si el ID existe -> actualiza el registro
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //En lugar de borrar registros fisicamente, se "desactiva" el cliente
    //Esto es una buena practica llamada "borrado logico"
    public void inactivar(Long id){
        //Busca el cliente; si no lo encuentra, lanza excepcion.
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a inactivo
        usuario.setEstatus("Inactivo");

        //Guarda el cambio en la BD
        usuarioRepository.save(usuario);
    }

    //Activa un usuario previamente desactivado
    public void activar(Long id){
        //Busca el usuario; si no lo encuentra, lanza excepcion.
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a activo
        usuario.setEstatus("Activo");

        //Guarda el cambio en la BD
        usuarioRepository.save(usuario);
    }

}
