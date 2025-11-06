//Clase padre
public class Auto {

    String marca;
    int modelo;

    void tipoMotor () {
        System.out.println("Motor generico");
    }

    String marca() {
        marca = "Generico";
        return marca;
    }

    int modelo() {
        modelo = 2000;
        return modelo;
    }
}
