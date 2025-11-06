public class PickUp extends Auto{

    @Override
    void tipoMotor( ) {
        System.out.println("Gasolina");
    }
    @Override
    String marca() {
        marca = "Chevrolet";
        return marca;
    }
    @Override
    int modelo() {
        modelo = 2025;
        return modelo;
    }
}
