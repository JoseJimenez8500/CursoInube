package com.inube.inube_service.service;

import com.inube.inube_service.entity.AbaCliente;
import com.inube.inube_service.repository.AbaClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AbaClienteService {
    private AbaClienteRepository abaClienteRepository;

    //Metodo: obtener todos los clientes
    @Transactional(readOnly= true)
    public List<AbaCliente> findAll(){
        return abaClienteRepository.findAll();
    }
}
