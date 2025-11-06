public class Autobus extends Auto{
    @Override
    void tipoMotor( ) {
        System.out.println("Diesel");
    }
    @Override
    String marca() {
        marca = "Volvo";
        return marca;
    }
    @Override
    int modelo() {
        modelo = 2022;
        return modelo;
    }
}
