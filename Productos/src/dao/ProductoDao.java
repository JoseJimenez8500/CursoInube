package dao;

import model.Producto;

import java.util.List;

/**
 * Interfaz que define el contrato para las operaciones relacionadas con prductos
 *
 * En una arquitectura limpia o por capas, repositorio se encarga de la comunicacion
 * con la fuente de datos (por ejemplo, un servicio externo o una base de datos)
 */

public interface ProductoDao {


    void guardar(Producto producto);

    void borrar (String idProducto);

    List<Producto> obtenerProductos();



}
