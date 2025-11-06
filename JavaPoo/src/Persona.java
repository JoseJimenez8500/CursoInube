public class Persona {

    /**
     * ATRIBUTOS
     * 'String nombre' -> guarda el nombre de la persona
     * 'int edad' -> guerda la edad de la persona
     * no tienen modificador private aqui pero se recomienda usarlo
     * para aplicar mejor la encapsulacion
     */
    String nombre;
    int edad;

    //CONSTRUCTOR

    /**
     *El constructor se llama igual que la clase 'Persona'
     * se ejecuta automaticamente cuando se crea un objeto tipo persona
     * Sirve para inicializar los atibutos con valores al momento de crear el objeto
     */
    public Persona(String nombre, int edad) {
        //'this.nombre = nombre' -> se refiere al atributo de la clase
        //'nombre' -> es el parametro recibido
        //Se usa this para diferenciar entre ambos
        this.nombre = nombre;
        this.edad = edad;
    }

    //METODO
    //metodo llamado saludar que no devuelve nada (void)
    //muestra un saludo personalizado con el nombre de la persona

    void saludar(){
        System.out.println("Hola, soy "+nombre);
    }

}
