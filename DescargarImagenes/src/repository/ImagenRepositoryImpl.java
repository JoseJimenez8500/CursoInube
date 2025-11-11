package repository;

import config.OracleConnection;
import model.Imagen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que implementa la Intefraz ImagenRepository
 *
 * Su funcion es decarar una imagen desde una api externa y guardar su informacion
 * (metadatos) en una base de datos Oracle
 */
public class ImagenRepositoryImpl implements  ImagenRepository {

    /**
     * Implmentacion del metodo desfcragraYGuradar
     *
     * @param apiURL URL desde donde se descargara la imagen
     * @param carpetaDestino Carpeta local donde se almacenara la imagen descargada
     * @return Un objeto imagen con la infromacion generada (id, URL, nombre de archivo)
     */

    @Override
    public Imagen descargarYGuardar(String apiURL, String carpetaDestino) {

        try {
            //1. Descargar imagen de la API

            //Crea un objeto URL con la direccion proporcionada
            URL url = new URL(apiURL);

            //Abre la conexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Define el metodo HTTP como GET (descarga de datos)
            connection.setRequestMethod("GET");

            //Genera un nombre unico para la imagen usando la hora actual del sistema
            String nombreArchivo = "imagen_"+ System.currentTimeMillis() + ".jpg";

            //Crea el archivo de destino donde se guardara la imagen
            File destino = new File(carpetaDestino, nombreArchivo);

            //Si la carpeta no existe se crea automaticamente
            if(!destino.getParentFile().exists()) {
                destino.getParentFile().mkdir();
            }

            //Abre flujos de esntrada/salida para copiar los datos de la red al disco
            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(destino)) {

                byte[] buffer = new byte[1024]; //tamaño del bloque de lectura
                int bytesLeidos;

                //lee los bytes de la conexion y los escribe en el archivo local
                while ((bytesLeidos = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesLeidos);
                }
            }

            System.out.println("Imagen descargada: " + destino.getAbsolutePath());

            //Crea un objeto imagen con los datos de la descarga
            Imagen imagen = new Imagen(apiURL, nombreArchivo);

            //2. Guardar metadatos en la base de datos Oracle
            guardarEnBase(imagen);

            //devulve el objeto Imagen creado
            return imagen;



        } catch (IOException e) {
            //Captura y muestra errores relacionados con la descarga o escritura del archivo
            System.out.println("Error al descargar imagen: " + e.getMessage());
            return null;
        }
    }

    /**
     * Metodo privado que gurada los metadatos de la imagen en la base de datos Oracle
     *
     * @param imagen Objeto Imagen con la informacion a almacenar
     */

    private void guardarEnBase(Imagen imagen){
        //consulta SQL oara insertar los datos en la tabla Imagenes
        String sql = "INSERT INTO IMAGENES (ID, URL, NOMBRE_ARCHIVO) VALUES (?, ?, ?)";

        //try-with-resources: garantiza que la conexion y el PreparedStatement se cierren automaticamente
        try (Connection conn = OracleConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ){
            //Asignamos los valores de la imagen a los parametros de consulta
            ps.setString(1, imagen.getId());
            ps.setString(2, imagen.getUrl());
            ps.setString(3, imagen.getNombreArchivo());

            //ejecuta la insercion en la base de datos
            ps.executeUpdate();

            System.out.println("Imagen guardada en Oracle: " + imagen.getNombreArchivo());

        } catch (SQLException e) {
            //Captura errores relacionados con la base de datos
            System.out.println("Error al guardar en Oracle: " + e.getMessage());
        }
    }
}
