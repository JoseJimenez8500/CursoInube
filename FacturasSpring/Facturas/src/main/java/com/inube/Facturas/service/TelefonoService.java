package com.inube.Facturas.service;

import com.inube.Facturas.model.Telefono;
import com.inube.Facturas.repository.TelefonoRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefonoService {

    private final TelefonoRespository telefonoRespository;

    public TelefonoService(TelefonoRespository telefonoRespository) {
        this.telefonoRespository = telefonoRespository;
    }

    public List<Telefono> findAll(){
        return telefonoRespository.findAll();
    }

    public List<Telefono> findActivos(){
        return telefonoRespository.findByActivo(1);
    }

    public List<Telefono> findInactivo(){
        return telefonoRespository.findByActivo(0);
    }

    public Optional<Telefono> findById(Long id){
        return telefonoRespository.findById(id);
    }

    public Optional<Telefono> findByIdWithClientes(Long id){
        return telefonoRespository.findByIdWithClientes(id);
    }

    public Telefono save(Telefono telefono){
        return telefonoRespository.save(telefono);
    }

    public void inactivar(Long id){
        Telefono telefono = telefonoRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));
        telefono.setActivo(0);
        telefonoRespository.save(telefono);
    }

    public void Activar(Long id){
        Telefono telefono = telefonoRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));
        telefono.setActivo(1);
        telefonoRespository.save(telefono);
    }
}
