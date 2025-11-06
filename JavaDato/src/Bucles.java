import java.util.List;

public class Bucles {
    public static void main(String[] args) {
        //ejemplos de bucles

        //while
        int contador = 1;
        while(contador<=5){
            System.out.println("Contador: "+contador);
            contador++;
        }

        //Do while
        int numero = 1;
        do {
            System.out.println("Numero: "+numero);
            numero++;
        }while(numero<=5);

        //for clasico
        for (int i = 0; i <=5 ; i++) {
            System.out.println("Iteracion: "+i);
        }

        //for each (enhanced for)
        String[]frutas = {"Manzana","Banana","Naranja","Cereza"};
        for (String fruta : frutas) {
            System.out.println("Fruta: "+fruta);
        }

        List<String> nombres = List.of("Ana", "Pedro", "Luis", "Maria");

        //for each para listas
        nombres.forEach(System.out::println);

        //for each para listas
        nombres.forEach(n -> System.out.println(n.toUpperCase()));

        //Stream (Desde java 8)
        //Stream forEach con lambda
        nombres.stream()
                .forEach(nombre -> System.out.println("Nombre: "+nombre));

        //Stream Filtrar y recorrer
        System.out.println("\nNombres con mas de 3 letras");
        nombres.stream()
                .filter(n -> n.length() > 3)
                .forEach(System.out::println);

        /**
         * Usa for-each cuando solo necesitas recorrer la coleccion sin indices
         * Usa while o do-while si no sabes cuantas iteraciones habra (ejemplo: leer hasta que el usuario escriba "Salir")
         * Evita bucles infinitos asegurandote de actualizar la condicion dentro del ciclo
         * prefiere streams cuando necesites operaciones de filtrado, mapeo o transformaciones en colecciones
         * no mezcles demasiada logica dentro del bucle -> extrae a metodos si es necesario
         */

    }
}
