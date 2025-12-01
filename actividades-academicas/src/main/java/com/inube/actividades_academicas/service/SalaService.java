package com.inube.actividades_academicas.service;

import com.inube.actividades_academicas.model.Sala;
import com.inube.actividades_academicas.model.Usuario;
import com.inube.actividades_academicas.repository.SalaRepository;
import com.inube.actividades_academicas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    //Dependencia del repositorio que maneja la BD
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    //Obtiene todos los clientes de la base de datos.
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public List<Sala> findEstatusActivo() {
        return salaRepository.findByEstatus("Activo");
    }

    //Regresa unicamente clientes inactivos (ACTIVO = 0)
    public List<Sala> findEstatusInactivo() {
        return salaRepository.findByEstatus("Inactivo");
    }

    //Busca un usuario por ID usando el metodo basico de JpaaRepository
    public Optional<Sala> findById(Long id) {
        return salaRepository.findById(id);
    }

    //Guarda o actualiza un cliente
    //si el ID es null -> crea uno nuevo
    //si el ID existe -> actualiza el registro
    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    //En lugar de borrar registros fisicamente, se "desactiva" el cliente
    //Esto es una buena practica llamada "borrado logico"
    public void inactivar(Long id){
        //Busca el cliente; si no lo encuentra, lanza excepcion.
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a inactivo
        sala.setEstatus("Inactivo");

        //Guarda el cambio en la BD
        salaRepository.save(sala);
    }

    //Activa un usuario previamente desactivado
    public void activar(Long id){
        //Busca el usuario; si no lo encuentra, lanza excepcion.
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a activo
        sala.setEstatus("Activo");

        //Guarda el cambio en la BD
        salaRepository.save(sala);
    }
}
