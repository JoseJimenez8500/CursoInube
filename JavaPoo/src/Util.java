//Miembros estaticos y constantes
//Declaramos una clase publica llamada Util
public class Util {
    /**
     * public -> indica que el metodo puede ser usado desde cualquier otra clase
     * static -> Significa que peretenece a la clase, no a un objeto
     * int -> es el tipo de dato que el metodo devuelve (un numero entero)
     * sumar -> es el nombre del metodo
     * (int a, int b) -> son los parametros de entrada: dos numeros enteros que se van a sumar
     */

    public static int sumar (int a, int b) {
        //return -> devuelve el resultado de la suma a quien llame al metodo
        return a + b;
    }

    /**
     * public -> la constante puede ser usada desde cualquie clase
     * static -> petenece a la clase no aun objeto
     * final -> su valor no se puede cambiar (constante)
     * double -> tipo de dato para numeros decimales
     * PI -> nombre de la contsante, por convencion se escribe en mayusculas
     * 3.1416 -> valor asignado a la constante PI
     */

    public static final double PI = 3.1416;

}
