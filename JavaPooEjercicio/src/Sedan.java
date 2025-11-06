public class Sedan extends Auto{


    @Override
    void tipoMotor( ) {
        System.out.println("Electrico");
    }
    @Override
    String marca() {
        marca = "Tesla";
        return marca;
    }
    @Override
    int modelo() {
        modelo = 2024;
        return modelo;
    }
}
