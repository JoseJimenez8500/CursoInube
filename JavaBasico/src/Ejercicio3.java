import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int num = Integer.parseInt(sc.nextLine());

        boolean bandera = num>0;
        if(num == 0){
            System.out.println("El numero es 0");
        }else if(num>0 && num%2==0){
            System.out.println("El numero es par y es positivo");
        }else if(num<0 && num%2==0){
            System.out.println("El numero es par y es negativo");
        }else if(num>0 && num%2>0){
            System.out.println("El numero es impar y es positivo");
        }else System.out.println("El numero es impar y es negativo");
    }
}
