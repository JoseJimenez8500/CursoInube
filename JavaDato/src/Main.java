/**
 * Clase pricnipal del proyecto
 * @autor Jose Alejandro Jimenez Preciado
 * version 1.0
 */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
/**
 * Metodo principal de aplicacion
 * @param args argumentos de linea de comando
 * (no se usan en esta aplicacion)
  */

/**
 * el byte es un entero pequeño que va del
 * -128 al 127, ocupa un byte "8 bits"
 */
        byte edad = 25;
    System.out.println("byte edad= "+edad);

    //es un tipo de dato entero mediano que va del -32768 al 32767, ocupa 2 bytes
        short anio = 1900;
    System.out.println("short anio= "+anio);

    //entero estandar (2,147,483,678  2,147,483,647), ocupa 4 bytes
        int dia = 2020;
    System.out.println("int dia= "+dia);

    //long: entero muy grande, ocupa 8 bytes
        long poblacionMundial = 2020;
    System.out.println("long poblacionMundial= "+poblacionMundial);

    //float: Numero decimal de 32 bits (menos preciso que double)
        float precio = 99.99f;
    System.out.println("float precio= "+precio);

    //double: numero decimal de 64 bits (mas preciso)
        double pi = 3.1416;
        System.out.println("double pi= "+pi);

    //char: caracter unico en unicode (16 bytes)
        char inicial = 'C';
        System.out.println("char inicial= "+inicial);

    //boolean: valor logico true/false
        boolean esJavaGenial = true;
        System.out.println("boolean esJavaGenial= "+esJavaGenial);

    //  OPERACIONES BASICAS

    int suma = edad + anio; //byte y short se convierten en int en operaciones
        System.out.println("Operacion suma: edad + anio= "+suma);

    int resta = anio-edad; //byte y short se convierten en int en operaciones
        System.out.println("Operacion resta: año - edad= "+resta);

    double division = pi/2;
        System.out.println("Operacion division: pi/2 = "+division);

    double multiplicacion = pi*2;
    System.out.println("Operacion multiplicacion: pi*2 = "+multiplicacion);

    boolean comparacion = poblacionMundial > anio;
        System.out.println("¿La poblacion de la ciudad es mayor a 1,000,000?"+comparacion);

    //convertir de un tipo a otro (cast)
    int precioEntero = (int)precio; //se trunca la parte decimal de 99.99f se convierte a 99
        System.out.println("Casting float -> int, precioEntero"+precioEntero);
    }
}