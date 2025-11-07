import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el primer numero: ");
        int numero1 = Integer.parseInt(sc.nextLine());

        System.out.print("Ingresa el segundo numero: ");
        int numero2 = Integer.parseInt(sc.nextLine());

        System.out.print("ingresa el tercer numero: ");
        int numero3 = Integer.parseInt(sc.nextLine());

        if(numero1>numero2 && numero1>numero3){
            System.out.println("El numero "+numero1+" es mayor");
        }else if(numero2>numero1 && numero2>numero3){
            System.out.println("El numero "+numero2+" es mayor");
        }else if(numero3>numero1 && numero3>numero2){
            System.out.println("El numero "+numero3+" es mayor");
        } else if (numero1==numero2 || numero1==numero3) {
            System.out.println();
        }

        if(numero1==numero2 && numero1==numero3 && numero2==numero3){
            System.out.println("todos los numeros son iguales");
        }else  if(numero1==numero2){
            System.out.println("el primer y segundo numero son iguales");
        } else if (numero1 == numero3) {
            System.out.println("el primer y tercer numero son iguales");
        } else if (numero2==numero3) {
            System.out.println("el segundo y tercer numero son iguales");
        }
    }
}
