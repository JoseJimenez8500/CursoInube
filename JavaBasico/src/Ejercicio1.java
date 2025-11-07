import java.time.LocalDate;
import java.util.Scanner;

public class Ejercicio1 {
    //Crea un programa que pida tu nombre y edad por consola, y luego imprima un mensaje
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Pedir nombre
        System.out.print("Ingresa tu nombre: ");
        String nombre = input.nextLine();

        //Pedir edad
        System.out.print("Ingresa tu edad: ");
        int edad = Integer.parseInt(input.nextLine());

        //Caluclar año de nacimiento
        LocalDate anio = LocalDate.now();
        int fechaNacimiento = anio.getYear()-edad;

        System.out.println("Hola " +nombre +" tienes "+edad+" años, naciste en el año "+ fechaNacimiento);
    }
}
