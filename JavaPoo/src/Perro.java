/**
 * Declaramos la clase Perro que hereda (Extends) de la clase animal
 * Gracias a la herencia Perro tiene acceso a los metodos y atributos de la clase Animal
 */

public class Perro extends Animal {
    /**
     * la anotacion @Override indica que estamos sobreescribiendo (reescribiendo)
     * el metodo 'hacerSonido' heredado de la clase animal
     */

    @Override
    void hacerSonido() {
        /**
         * En lugar de hacer el sonudo generico de la clase padre ("Sonido generico")
         * el perro define su propio comportamiento: imprimir "Guau".
         */
        System.out.println("Guau");
    }
}
