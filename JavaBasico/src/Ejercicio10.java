import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int opcion=0;
        int numero1=0;
        int numero2=0;
        int operacion=0;
        do {
            System.out.println("\n1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el primer numero");
                    numero1 = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el segundo numero");
                    numero2 = Integer.parseInt(sc.nextLine());
                    operacion = numero1+numero2;
                    System.out.println("La suma es: "+operacion);
                    break;
                case 2:
                    System.out.println("Ingrese el primer numero");
                    numero1 = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el segundo numero");
                    numero2 = Integer.parseInt(sc.nextLine());
                    operacion = numero1-numero2;
                    System.out.println("La resta es: "+operacion);
                    break;
                case 3:
                    System.out.println("Ingrese el primer numero");
                    numero1 = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el segundo numero");
                    numero2 = Integer.parseInt(sc.nextLine());
                    operacion = numero1*numero2;
                    System.out.println("La multiplicacion es: "+operacion);
                    break;
                case 4:
                    System.out.println("Ingrese el primer numero");
                    numero1 = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el segundo numero");
                    numero2 = Integer.parseInt(sc.nextLine());
                    if(numero2 == 0){
                        System.out.println("Error: división entre cero");
                        break;
                    }
                    operacion = numero1/numero2;
                    System.out.println("La division es: "+operacion);
                    break;
                case 5: System.out.println("Saliendo"); break;
                default:
                    System.out.println("Introduce una opcion valida");

            }
        }while (opcion != 5);
    }
}
