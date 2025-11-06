import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Listas {
    public static void main(String[] args) {
        //ejempli de listas

        //Usar la interfaz List y no la implementacion concreta
        List<String> nombres = new ArrayList<>();
        nombres.add("Carlos");
        nombres.add("Ana");
        nombres.add("Pedro");
        nombres.add("Lucia");

        System.out.println("Lista original: "+nombres);

        //Acceder a un elemento
        String primero = nombres.get(0);
        System.out.println("Primer elemento: "+primero);

        //Iterar con for tradicional
        System.out.println("\nIteracion con for tradicional:");
        for (int i = 0; i<nombres.size();i++) {
            System.out.println("Indice "+i+": "+nombres.get(i));
        }

        //Iterar con while
        System.out.println("\nIteracion con while:");
        int index = 0;
        while (index<nombres.size()) {
            System.out.println("Indice "+index+": "+nombres.get(index));
            index++;
        }

        //Iterar con foreach y lambda
        System.out.println("\nIteracion con foreach (lambda):");
        nombres.forEach(nombre -> System.out.println(" - "+nombre));

        //Buscar elementos (contains)
        System.out.println("\n¿La lista contiene a Ana? "+nombres.contains("Ana"));

        //ordenar con un comparator
        Collections.sort(nombres);//orden alfabetico natural
        System.out.println("Lista ordenada alfabeticamente"+nombres);

        //orden inverso con comparator
        nombres.sort(Comparator.reverseOrder());
        System.out.println("Lista ordenada inversamente"+nombres);

        //eliminar un elemento
        nombres.remove("Pedro");
        System.out.println("\nLista despues de eliminar a Pedro: "+nombres);

        //Filtrar por strams
        System.out.println("\n Nombres que empiezan con 'C'");
        nombres.stream().filter(nombre -> nombre.startsWith("C")).forEach(System.out::println);

        //convertir a inmutable (Buena practica si no debe modificarse)
        List<String> nombresInmutables = List.copyOf(nombres);
        System.out.println("\nLista inmutable: "+nombresInmutables);


    }


}

