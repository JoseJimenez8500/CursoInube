import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String numero="";
        System.out.println("Ingresa numeros, escriba 'no' para terminar la suma");

        int n=0;
        List<Integer> lista=new ArrayList<>();

        while (!numero.equals("no")){
            System.out.print("Ingresa un numero: ");
            numero = sc.nextLine();

            if(!numero.equals("no")){
                n =  Integer.parseInt(numero);
                n += n;
                lista.add(n);
            }
        }
        System.out.println("La suma es: "+n);
        System.out.println("Ingreso "+lista.size()+" numeros");
    }
}
