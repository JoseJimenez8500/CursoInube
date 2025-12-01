package com.inube.actividades_academicas.repository;


import com.inube.actividades_academicas.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRespository extends JpaRepository<Actividad, Long> {

    List<Actividad> findByEstatus(String estatus);
}
