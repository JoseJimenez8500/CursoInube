package controller;

import model.Imagen;
import repository.ImagenRepository;
import service.ImagenService;

import java.util.List;

public class ImagenController {

    //servicio que contiene la Logica de negocio para descargar imagenes
    private final ImagenService service;

    public ImagenController() {

        //se crea una instancia del servicio
        //el controlador no hace la logica, solo delega
        this.service = new ImagenService();
    }

    public void descargarVariasImagenes(String apiUrl, String carpetaDestino, int cantidad){
        //Se delega la logica de descarga al servicio
        List<Imagen> imagenes = service.descargarVarias(apiUrl, carpetaDestino, cantidad);

        // se muestra el totoal de imagenes descargadas
        System.out.println("Total de imagenes descargadas: " + imagenes.size());

        //Se imprimen los datos de cada imagen descragada
        //usando una referencia a metodo (::)
        imagenes.forEach(System.out::println);
    }

}
