package model;

//Importamos la clase UUID para generar identificadores unicos
import java.util.UUID;

//Definicion de la clase imagen
public class Imagen {

    //Atributos privados (Encapsulados) de la clase
    private String id;              //Identificador unico de la imagen
    private String url;             //Url donde se encuantra almacenada ia imagen
    private String nombreArchivo;   //Nombre del archivo de la imagen

    /**
     * Constructor de la clase Imagen
     * Este metodo se ejecuta al crear un nuevo objeto de la imagen
     *
     * @param url directorio donde esta almacenada la imagen
     * @param nombreArchivo nombre del archivo de la imagen
     */

    //genera automaticamente un UUID al crear la imagen
     public Imagen(String url, String nombreArchivo) {
         //Genera un identificador unico (UUID) de manera automatica
         this.id = UUID.randomUUID().toString();
         //Asigna la URL pasada por parametro al atributo de la clase
         this.url = url;
         //Asigna el nombre del archivo pasado por parameto
         this.nombreArchivo = nombreArchivo;
     }

     //Devuelve el ID de la Imagen
    public String getId() {
        return id;
    }

    //Devuelve la URL de la imagen
    public String getUrl() {
        return url;
    }

    //Devuelve el nombre del archivo de la imagen
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                '}';
    }
}
