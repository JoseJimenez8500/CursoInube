import config.OracleConnection;
import controller.ImagenController;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Inicizalizar conexion Singleton
        OracleConnection.getInstance();

        //Configuracion general
        String apiUrl = "http://picsum.photos/300/200";
        String carpetaDestino = "imagenes_descargadas";

        //Leer cantidad desde consola
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuantas imagenes quieres descragar?: ");
        int cantidad = sc.nextInt();

        ImagenController controller = new ImagenController();
        controller.descargarVariasImagenes(apiUrl, carpetaDestino, cantidad);
    }
}