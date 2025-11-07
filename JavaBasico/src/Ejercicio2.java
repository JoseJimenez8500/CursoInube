import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el primer numero: ");
        int numero1 = Integer.parseInt(sc.nextLine());

        System.out.print("Ingresa el segundo numero: ");
        int numero2 = Integer.parseInt(sc.nextLine());

        System.out.println("\nElige una operacion: ");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicacion");
        System.out.println("4. Division");

        String opcion = sc.nextLine();
        switch(opcion.toLowerCase()){
            case "1","suma" -> System.out.println("el resultado de la operacion es: "+(numero1+numero2));
            case "2","resta" -> System.out.println("el resultado de la operacion es:"+(numero1-numero2));
            case "3","multiplicacion" -> System.out.println("el resultado de la operacion es:"+(numero1*numero2));
            case "4", "division" -> {
                if(numero2 == 0){
                    System.out.println("Error: división entre cero");
                    break;
                }
                System.out.println("el resultado de la operacion es:"+(numero1/numero2));
            }
            default -> System.out.println("elige una opcion válida");
        }
    }
}
