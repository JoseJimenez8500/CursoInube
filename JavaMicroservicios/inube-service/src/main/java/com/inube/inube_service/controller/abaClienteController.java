package com.inube.inube_service.controller;

import com.inube.inube_service.entity.AbaCliente;
import com.inube.inube_service.repository.AbaClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/inube/service")
public class abaClienteController {

    private final AbaClienteRepository abaClienteRepository;

    @GetMapping("/list")
    public ResponseEntity<List<AbaCliente>> findAll(){
        return ResponseEntity.ok(abaClienteRepository.findAll());
    }

}
