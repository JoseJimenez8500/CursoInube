package com.inube.actividades_academicas.repository;

import com.inube.actividades_academicas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @Repository indica que esta interfaz es un componente de acceso a datos (DAO)
 * Spring detecta automaticamente esta capa y la convierte en un bean para onyectarlo donde sea necesario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //SELECT * FROM USUARIO WHERE ESTATUS = ?
    List<Usuario> findByEstatus(String estatus);
}
