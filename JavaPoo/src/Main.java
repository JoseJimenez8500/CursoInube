//Clase principal del programa
public class Main {
    /**
     * el metodo main es el punto de entrada de cualuier aplicacion Java
     * String[] args
     */
    public static void main(String[] args) {
        /**
         * CLASE Y OBJETO
         * se crea un objeto p a partir de la clase persona
         * se llama al contructor con los valores "Oscar" y 25
         */
        Persona p = new Persona("Oscar",25);

        /**
         * llamamos al metodo saludar del objeto p
         * este metodo imprime un mensaje en consola
         */
        p.saludar();

        /**
         * ENCAPSULACION
         * creamos un onjeto cuenta de tipo CuentaBancaria
         * La encapsulacion permite proteger el atributo saldo
         * y acceder a el solo mediante metodos publicos
         */
        CuentaBancaria cuenta = new CuentaBancaria();

        //Usamos el metodo depositar para aumentar el saldo
        cuenta.depositar(1500);

        /**
         * Consultamos el saldo usando el metodo getSaldo
         * sin acceder directamente al atributo privado
         */

        /**
         * POLIMORFISMO
         * creamos objetos de tipo animal pero que realmente son gato y perro
         * esto demuestra el polimorfismo: un mismo tipo (Animal) se comporta distinto
         * segun el objeto que representa
         */
        Animal a1 = new Gato();
        Animal a2 = new Perro();

        /**
         * llamamos al metodo hacerSonido en cada objeto
         * cada clase hija (Gato, Perro) tiene su propia implementacion.
         */
        a1.hacerSonido();
        a2.hacerSonido();

        /**
         * ESTATICOS Y CONSTANTES
         * Usamos el metodo estatico sumar de la clase Util sin crear un objeto
         * los metodos estaticos se pueden usar directamente desde la clase
         */
        System.out.println("Suma: "+Util.sumar(5,7));

        /**
         * Imprimimos la contante PI definida de la clase Util
         * Las constantes final no pueden cambiar su valor
         */

        System.out.println(" Valor de PI: "+ Util.PI);

    }
}