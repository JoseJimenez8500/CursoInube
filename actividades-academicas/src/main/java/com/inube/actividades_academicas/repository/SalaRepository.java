package com.inube.actividades_academicas.repository;

import com.inube.actividades_academicas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByEstatus(String estatus);
}
