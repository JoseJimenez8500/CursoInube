package com.inube.actividades_academicas.service;

import com.inube.actividades_academicas.model.Actividad;
import com.inube.actividades_academicas.repository.ActividadRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    private final ActividadRespository actividadRespository;

    public ActividadService(ActividadRespository actividadRespository   ) {
        this.actividadRespository = actividadRespository;
    }

    public List<Actividad> findAll(){
        return actividadRespository.findAll();
    }

    public List<Actividad> findActivos(){
        return actividadRespository.findByEstatus("Activo");
    }

    public List<Actividad> findInactivo(){
        return actividadRespository.findByEstatus("Inactivo");
    }

    public Optional<Actividad> findById(Long id){
        return actividadRespository.findById(id);
    }

    public Actividad save(Actividad actividad){
        return actividadRespository.save(actividad);
    }

    public void inactivar(Long id){
        Actividad actividad = actividadRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));
        actividad.setEstatus("Inactivo");
        actividadRespository.save(actividad);
    }

    public void Activar(Long id){
        Actividad actividad = actividadRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));
        actividad.setEstatus("Activo");
        actividadRespository.save(actividad);
    }
}
