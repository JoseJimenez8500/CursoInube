public class Animal {
    /**
     * HERENCIA Y POLIMORFISMO
     * declaramos una clase publica llamada animal
     * esta sera la clase base o clase padre de la cual heredaran otras clases
     * como perro y gato
     */

    /**
     * definimos un metodo llamado 'hacerSonido'
     * no tiene modificador 'public' por lo que su acceso por defecto es default
     * es decir, solo puede ser usado dentro del mismo paquete
     */

    void hacerSonido(){
        /**
         * Este metodo imprime un sonido generico
         * las clases hijas (Perro, Gato) podran sobreescribir este metodo
         * para personalizar el sonido que hacen
         */
        System.out.println("Sonido generico ");
    }
}
