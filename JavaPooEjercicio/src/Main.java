import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Auto auto1 = new Sedan();
        Auto auto2 = new PickUp();
        Auto auto3 = new Autobus();

        auto1.tipoMotor();
        auto2.tipoMotor();
        auto3.tipoMotor();

        List<String> marcas = new ArrayList<>();
        marcas.add(auto1.marca());
        marcas.add(auto2.marca());
        marcas.add(auto3.marca());

        List<Integer> modelo = new ArrayList<>();
        modelo.add(auto1.modelo());
        modelo.add(auto2.modelo());
        modelo.add(auto3.modelo());

        System.out.println("Lista de Marcas: ");
        marcas.forEach(System.out::println);

        System.out.println("Lista de Modelos: ");
        marcas.forEach(System.out::println);

        //ordenar con un comparator
        Collections.sort(marcas);//orden alfabetico natural
        System.out.println("Lista ordenada alfabeticamente"+marcas);

        //eliminar un elemento
        marcas.remove("Volvo");
        System.out.println("\nLista despues de eliminar a Volvo: "+marcas);
    }
}