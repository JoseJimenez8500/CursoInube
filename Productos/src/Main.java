import config.OracleConnection;
import controller.ProductoController;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Inicizalizar conexion Singleton
        OracleConnection.getInstance();

        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());

        ProductoController controller = new ProductoController();
        controller.nuevoProducto("Chetos", 15, fecha);
    }
}