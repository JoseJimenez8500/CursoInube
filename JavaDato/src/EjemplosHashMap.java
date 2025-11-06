import java.util.HashMap;

public class EjemplosHashMap {
    public static void main(String[] args) {

        HashMap<String,Integer> edades = new HashMap<>();

        //agregar elementos
        edades.put("Oscar",25);
        edades.put("Maria",30);
        edades.put("Carlos",28);

        //Obtener un valor por su clave
        int edadOscar = edades.get("Oscar");
        System.out.println("edad de Oscar: "+edadOscar);

        //Verificar si una clave existe
        if(edades.containsKey("Maria")){
            System.out.println("Maria esta en el mapa");
        }

        //Eliminar una clave
        edades.remove("Carlos");

        //recorrer el mapa
        for(String nombre : edades.keySet()){
            System.out.println(nombre+" tiene "+edades.get(nombre)+" años");
        }

        //tamaño del hashmap
        System.out.println("Tamaño del mapa: "+edades.size());

    }
}
