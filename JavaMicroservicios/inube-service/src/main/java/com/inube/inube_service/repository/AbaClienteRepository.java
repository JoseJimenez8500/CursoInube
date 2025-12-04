package com.inube.inube_service.repository;

import com.inube.inube_service.entity.AbaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AbaClienteRepository extends JpaRepository<AbaCliente, UUID> {
    Optional<AbaCliente> findById(UUID id);

    Optional<AbaCliente> findByNombre(String nombre);

}
