package service;

import factory.RespositoryFactory;
import model.Imagen;
import repository.ImagenRepository;

import java.util.ArrayList;
import java.util.List;

public class ImagenService {
    //Dependencia hacia el repositorio encargado de descargar y guardar imagenes
    private final ImagenRepository repository;

    public ImagenService() {
        /**
         * Se obtiene automaticamente la implementacion concreta desde una fabrica
         * esto permite cambiar la implementacion (Oracle, Local, remota, mock, etc)
         * sin modificar el codigo del sercicio
         */
        this.repository = RespositoryFactory.getImagenRepository();
    }

    /**
     * Descraga varias Imagenes desde la API y las guarda en una carpeta destino
     *
     * @param apiUrl URL del endpoint que devuelve una imagen (cada llamada descarga una)
     * @param carpetaDestino Carpeta local donde se gurdaran las imagenes
     * @param cantidad numero de imagenes a descargar
     * @return Lista de objetos Imagen con la informacion de cada archivo guardado
     */

    public List<Imagen> descargarVarias(String apiUrl, String carpetaDestino, int cantidad){

        //Lista donde se iran guardando las imagenes exotosamente descargados
        List<Imagen> imagenes = new ArrayList<>();

        //Bucle qye repote la descarga 'cantidad' veces
        for(int i = 1; i <= cantidad; i++){
            //mensaje  informativo para el ususario
            System.out.println("descargando imagen " + i + " de " + cantidad + "...");

            //Llamamos al repositorio para descargar y guardar la imagen
            //La logica de conexion HHTP y almacenamiento esta separada aqui
            Imagen img = repository.descargarYGuardar(apiUrl, carpetaDestino);

            //solo añadimos a la lista si se descargo correctamente
            if(img != null){
                imagenes.add(img);
            }
        }
        //Se retorna la lista con todas la imagenes descargadas
        return imagenes;
    }
}
