import java.util.Random;
import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {
        Random aleatorio = new Random();
        int r=aleatorio.nextInt(50)+1; //genera un numero entre 1 y 50

        Scanner leer = new Scanner(System.in);
        int numero=0;
        int contador=0;

        while(!(numero==r)){
            System.out.print("Adivina el numero entre 1 y 50: ");
            numero = Integer.parseInt(leer.nextLine());
            if(numero>r){
                System.out.println("El numero que ingresaste es mayor\n");
            }else if(numero<r) System.out.println("El numero que ingresaste es menor\n");
            contador++;
        }
        System.out.println("numero correcto, se utilizaron "+contador+" intentos");
    }
}
