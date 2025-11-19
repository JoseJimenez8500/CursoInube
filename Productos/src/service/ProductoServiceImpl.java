package service;

import dao.ProductoDaoImpl;
import model.Producto;
import dao.ProductoDao;

import java.util.ArrayList;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    //Dependencia hacia el repositorio encargado de gestionar productos
    private final ProductoDao repository = new ProductoDaoImpl();;




    @Override
    public void guardar(Producto producto) {

        //LLamada a la capa Dao
        repository.guardar(producto);
    }

    @Override
    public void borrar(String idProducto) {
        repository.borrar(idProducto);
    }

    @Override
    public List<Producto> obtenerProductos() {
        return repository.obtenerProductos();
    }
}
