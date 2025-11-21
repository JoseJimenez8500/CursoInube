package com.inube.Facturas.repository;

import com.inube.Facturas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


/**
 * @Repository indica que esta interfaz es un componente de acceso a datos (DAO)
 * Spring detecta automaticamente esta capa y la convierte en un bean para onyectarlo donde sea necesario
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Consulta personalizada usando JPQL
     * LEFT JOIN FETCH: carga al cliente y sus telefonos en una sola consulta (Evita Lazy)
     * WHERE c.idCliente = :id -> filtra por el ID recibido como parametro.
     *
     * Optional<Cliente>: devuelve un cliente envuelto en Optional para manejar el caso de "no encontrado"
     */

    @Query("""
            SELECT c
            FROM Cliente c
            LEFT JOIN FETCH c.telefonos
            WHERE c.idCliente = :id
            """)
    Optional<Cliente> findByIdWithPhones(Long id);


    //Spring Data JPA genera automaticamente esta consulta basada en el nombre del metodo.
    //Select * from CLIENTES Where APATERNO = ?
    //List<Cliente> findByAPaterno(String apellido);

    //Igual que arriba: genera la consulta automaticamente.
    //SELECT * FROM CLIENTES WHERE ACTIVO = ?
    List<Cliente> findByActivo(Integer activo);
}
