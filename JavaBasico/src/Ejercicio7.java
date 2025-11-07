import java.util.ArrayList;
import java.util.List;

public class Ejercicio7 {
    public static void main(String[] args) {
        System.out.println("Imprimir todos los numeros pares del 1 al 100");

        List<Integer> lista = new ArrayList<>();

        for (int i = 1; i <= 100; i++){
            if(i%2==0){
                System.out.println(i);
                lista.add(i);
            }
        }
        System.out.println("La lista del 1 al 100 contiene "+lista.size()+" numeros pares");
    }
}
