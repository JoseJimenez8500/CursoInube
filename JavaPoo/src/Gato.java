/**
 * Declaramos la clase Gato que hereda (Extends) de la clase animal
 * Gracias a la herencia Gato tiene acceso a los metodos y atributos de la clase Animal
 */

public class Gato extends  Animal {
    /**
     * la anotacion @Override indica que estamos sobreescribiendo (reescribiendo)
     * el metodo 'hacerSonido' heredado de la clase animal
     */

    @Override
    void hacerSonido() {
        /**
         * En lugar de hacer el sonudo generico de la clase padre ("Sonido generico")
         * el gato define su propio comportamiento: imprimir "Miau".
         */
        System.out.println("Miau");
    }

}
