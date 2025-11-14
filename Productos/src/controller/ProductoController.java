package controller;

import model.Producto;
import service.ProductoService;
import service.ProductoServiceImpl;

import java.sql.Date;
import java.util.List;

public class ProductoController {

    //servicio que contiene la Logica de negocio para descargar imagenes
    private final ProductoService service;

    public ProductoController() {
        //se crea una instancia del servicio
        //el controlador no hace la logica, solo delega
        this.service = new ProductoServiceImpl();
    }


    public void nuevoProducto(String nombre, double monto, Date fecha) {

        Producto producto = new Producto(nombre, monto, fecha);
        service.guardar(producto);
    }


}
