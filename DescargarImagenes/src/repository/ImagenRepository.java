package repository;

import model.Imagen;

/**
 * Interfaz que define el contrato para las operaciones relacionadas con imagenes
 *
 * En una arquitectura limpia o por capas, repositorio se encarga de la comunicacion
 * con la fuente de datos (por ejemplo, un servicio externo o una base de datos)
 */

public interface ImagenRepository {

    /**
     * Metodo abstracto que debe implementarse en una clase concreta
     *
     * su funcion es descargar una imagen desde una URL de una API y guardarla
     * en una copia local del sistema
     *
     */

    Imagen descargarYGuardar(String apiURL, String carpetaDestino);

}
