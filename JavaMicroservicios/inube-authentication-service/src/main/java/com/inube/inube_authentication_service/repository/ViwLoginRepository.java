package com.inube.inube_authentication_service.repository;

import com.inube.inube_authentication_service.entity.ViwLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//Respositorio para consultar la vista 'viwLogin' en la base de datos
//esta vista probablemente contiene datos necesarios para el proeso de login
//        (usuario, contraseña, estado, roles, etc.)
public interface ViwLoginRepository extends CrudRepository<ViwLogin, Integer> {

     //busca un registro dentro de la vista cuando el nombre de usuario.
     //Sprin Data Jpa genera automaticamente la consulta equivalente a:
    // SELECT * FROM viwLogin WHERE usuario = :usuario;
     //
     //como esta vista es de lectura, el repositorio se usa unicamente para consultar
     //y no para operaciones como save(), update() o delete
     Optional<ViwLogin> findByUsuario(String usuario);

}
