import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un numero: ");
        int numero = Integer.parseInt(sc.nextLine());
        int suma=0;
        if (numero>1){

            System.out.println("Numeros del 1 al "+numero);
            for (int i = 1; i <= numero; i++){
                suma=suma+i;
                System.out.print(i+" ");
            }
        }else System.out.println("Introduce un numero mayor a 1");
        System.out.println("\nLa suma de los numeros es: "+suma);
    }
}
