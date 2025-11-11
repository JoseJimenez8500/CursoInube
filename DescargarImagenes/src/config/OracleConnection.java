package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Clase responsble de administrar la conexion con la base de datos Oracle.
 *
 * Utiliza el patron Singleton para asegurar que solo exista una unica Instancia
 * de conexion durante la ejecucion del parograma
 */
public class OracleConnection {

    //Instancia unica de la clase (Singleton)
    private static OracleConnection instance;

    //Objeto Connection que repfresenta la conexion ectiva con la base de datos
    private Connection connection;

    //Datos de configuracion de la conexion a Oracle
    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "imagenes";
    private final String PASSWORD = "imagenes";

    /**
     * Constructor privado
     * Evita que otras clases creen instancias directamente
     * al inicializar. llama automaticamente al metodo connect()
     */

    private OracleConnection() {
        connect();
    }

    private void connect() {
        try {
            //carga el controlador JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //Establece la conexion con los datos definidos
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion Oracle establecida correctamente");
        } catch (ClassNotFoundException | SQLException e) {
            //Muestra el error si ocurre algn problema con el driver o la conexion
            System.out.println("Error al conectar con Oracle");
        }
    }

    /**
     * Metodo estatico que devuelve la instancia unica de la clase (Singleton)
     *
     * Implementa el patron de "doble verificacion" (double-checked locking)
     * para asgurar que la creacion sea segura en entornos multi hilos
     */

    public static OracleConnection getInstance() {
        if (instance == null) {
            synchronized (OracleConnection.class) {
                if(instance == null) {
                    instance = new OracleConnection();
                }
            }
        }
        return instance;
    }

    /**
     * devulve la conexion activa a la base de datos.
     *
     * si la conexion fue cerrada o es nula, intenta reconectarse, automaticamente.
     * @return Un objeto Connection valido y activo
     */

    public Connection getConnection() {
        try {
            //Si la conexion esta cerrada o nula, vuelve conectarse
            if(connection == null || connection.isClosed()) {
                System.out.println("Reconectando a Oracle");
                connect();
            }
        }catch (SQLException e) {
            //si hay error al verificar el estado, intenta reconectar
            System.out.println("Error verificando la conexion: "+ e.getMessage());
            connect();
        }
        return connection;
    }

}
