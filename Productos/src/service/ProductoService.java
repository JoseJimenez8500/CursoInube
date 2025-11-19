package service;

import model.Producto;

import java.util.List;

public interface ProductoService {

    void guardar(Producto producto);

    void borrar (String idProducto);

    List<Producto> obtenerProductos();
}
