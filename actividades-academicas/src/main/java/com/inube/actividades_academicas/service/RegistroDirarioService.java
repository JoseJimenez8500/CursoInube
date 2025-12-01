package com.inube.actividades_academicas.service;

import com.inube.actividades_academicas.model.Actividad;
import com.inube.actividades_academicas.model.RegistroDirario;
import com.inube.actividades_academicas.model.Usuario;
import com.inube.actividades_academicas.repository.RegistroDirarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroDirarioService {
    private final RegistroDirarioRepository registroDirarioRepository;

    public RegistroDirarioService(RegistroDirarioRepository registroDirarioRepository) {
        this.registroDirarioRepository = registroDirarioRepository;
    }

    public List<RegistroDirario> findAll() {
        return registroDirarioRepository.findAll();
    }
}
