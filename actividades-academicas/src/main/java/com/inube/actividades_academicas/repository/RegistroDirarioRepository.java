package com.inube.actividades_academicas.repository;

import com.inube.actividades_academicas.model.RegistroDirario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDirarioRepository extends JpaRepository<RegistroDirario, Integer> {
}
