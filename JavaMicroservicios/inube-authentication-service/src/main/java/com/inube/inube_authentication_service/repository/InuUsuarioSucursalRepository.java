package com.inube.inube_authentication_service.repository;

import com.inube.inube_authentication_service.entity.InuUsuarioSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
//Interfaz que maneja las operaciones de base de datos para la entidad InuUsuarioSuccursal
public interface InuUsuarioSucursalRepository extends JpaRepository<InuUsuarioSucursal, UUID> {

    //Busca todas la relaciones de sucursales asignadas a un usuario
    //el metodo sigue las convencion de nombres de SpringData
    //por lo que sprimg maneja automaticamente la consulta:
    //SELECT * FROM inu_usuario_sucurssal WHERE uuid_usuario
    List<InuUsuarioSucursal> findByUuidUsuario(UUID uuidUsuario);
}