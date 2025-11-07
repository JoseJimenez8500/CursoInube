import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un numero: ");
        int numero = Integer.parseInt(sc.nextLine());

        System.out.println("Hasta que numero desea mostrar la tabla de multiplicar: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {
            System.out.println(numero*i);
        }
    }
}
