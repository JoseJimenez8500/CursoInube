package com.inube.inube_authentication_service.repository;

import com.inube.inube_authentication_service.entity.InuUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
// Indica que esta interfaz es un componente de acceso a datos (DAO)
// y que Spring debe detectarlo automáticamente.
public interface InuUsuarioRepository extends JpaRepository<InuUsuario, UUID> {
    // Hereda de JpaRepository todas las operaciones CRUD:
    // save(), findById(), findAll(), delete(), etc.
    // El primer parámetro es la entidad y el segundo el tipo de su ID.

    // Busca un usuario por su nombre de usuario (username).
    // Spring Data genera automáticamente la consulta basada en el nombre del método.
    Optional<InuUsuario> findByNombreUsuario(String nombreUsuario);

    // Busca un usuario usando el UUID asociado a la persona.
    // También es un finder automático sin necesidad de escribir la consulta.
    Optional<InuUsuario> findByUuidPersona(UUID uuidPersona);
}
