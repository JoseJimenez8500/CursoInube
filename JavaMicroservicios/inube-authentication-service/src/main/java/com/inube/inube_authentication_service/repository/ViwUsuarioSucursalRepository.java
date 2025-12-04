package com.inube.inube_authentication_service.repository;

import com.inube.inube_authentication_service.entity.ViwUsuarioSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
//repostorio para consultar la vista 'ViwUsuarioSucursal'
// esta vista probablemente une informacion de usuarios y sus sucursales
// para simplificar consultas sin necesidad de escribir SQL manual

public interface ViwUsuarioSucursalRepository extends JpaRepository<ViwUsuarioSucursal, UUID> {

    //consulta todas la sucursales asociadas a un usuario especifico
    // Sprign DATA JPA genera automaticamente una consulta equivalente a:
    // SELECT * FROM viw_usuario_sucursal WHERE uuid_usuario :uuidUsuario;
    //Nota: como es una vista este repositorio solo debe usarse para CONSULTAR
    //(findBy..., findAll), no para operaciones de escritura (save, delete, etc.)
    List<ViwUsuarioSucursal> findByUuidUsuario(UUID uuidUsuario);
}
