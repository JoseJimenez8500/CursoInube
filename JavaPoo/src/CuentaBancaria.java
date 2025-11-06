public class CuentaBancaria {
    //ENCAPSULACION
    /**
     * Atributo privado que almacena el saldo de la cuenta
     * 'private' significa que solo se puede accceder a esta variable
     * desde dentro de esta clase (nadie fuera puede modificarlo directamente)
     */

    private double saldo;

    /**
     * Metodo publico para modificar el saldo
     * Metodo que permite depositar dinero en la cuenta
     * 'public' -> puede ser usado en otras clases
     * 'void' -> no devuelve ningun valor
     * 'monto' -> es el parametro que indica cuanro dinero se va a depositar
     */

    public void depositar(double monto){
        //agregamos el monto al salo actual
        saldo = saldo + monto;
    }

    /**
     * Metodo publico para consultar el saldo
     * este metodo devuelve el valor actual del saldo
     * se le llama getter porque obtiene el valor del atributo privado 'saldo'
     */

    public double getSaldo() {
        //Retornamos el valor de saldo para que otras clases puedan leerlo
        //pero sin poder modificarlo directamente
        return saldo;
    }
}
