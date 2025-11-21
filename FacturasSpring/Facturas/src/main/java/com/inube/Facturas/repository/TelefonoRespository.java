package com.inube.Facturas.repository;

import com.inube.Facturas.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelefonoRespository extends JpaRepository<Telefono, Long> {

    @Query("""
            SELECT t
            FROM Telefono t
            LEFT JOIN FETCH t.cliente
            WHERE t.idTelefono = :id
            """)
    Optional<Telefono> findByIdWithClientes(Long id);

    List<Telefono> findByActivo(Integer activo);
}
