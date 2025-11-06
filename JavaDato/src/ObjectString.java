public class ObjectString {
    public static void main(String[] args) {
        //Manejo de Object String en java

        //String literal (usa el String Pool)
        String x1 = "Hola";
        String x2 = "Hola";
        System.out.println("s1 == s2 ->" +(x1 == x2) ); //ambos apuntan al mismo objeto en el String Pool

        //String creado con new (Nuevo objeto en memoria heap)
        String x3 = new String("Hola");
        System.out.println("s1 == s3 ->" +(x1 == x3) );//false -> diferente referencia
        System.out.println("s1.equals(s3) -> "+ x1.equals(x3));//true -> contenido igual

        //Inmutabilidad de String
        String texto = "Java";
        texto.concat(" es genial");//no modifica el original
        System.out.println("\n Texto original (inmutable): "+texto);
        texto = texto.concat(" es genial");//reasigacion de valor en memoria
        texto += "es genial +++";               //reasigacion de valor en memoria
        texto += "en web";                      //reasigacion de valor en memoria
        texto += "y en aplicacion";             //reasigacion de valor en memoria
        texto += "de escritorio";               //reasigacion de valor en memoria
        texto = texto.replace("+++","fff");
        System.out.println("texto reasignado: "+texto);// reemplazo de una parte de la cadena

        //problema de concatenacion en bucles
        String concatMal = "";
        for (int i = 0; i < 5; i++) {
            concatMal += i + " ";
        }
        System.out.println("concatenacion ineficiente: "+concatMal);

        //Uso correcto con StringBuilder(Mutable)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i).append(" ").append(" Hola").append("¿Como estas? ")
                .append("c")
                .append("v");
        }
        System.out.println("concatenacion eficiente con StringBuilder: "+ sb.toString());

        //comparacion de rendimiento (ejemplo basico)
        long inicio1 = System.nanoTime();
        String test1 = "";
        for(int i = 0; i < 1000; i++) {
            test1 += i;                     //Mala practica
        }
        long fin1 = System.nanoTime();

        long inicio2 = System.nanoTime();

        StringBuilder test2 = new StringBuilder();
        for(int i = 0; i < 1000; i++) {
            test2.append(i);
        }
        long fin2 = System.nanoTime();

        System.out.println("\nTiempo concatenacion con String (ns)"+(fin1 - inicio1));
        System.out.println("Tiempo concatenacion con StringBuilder: "+ (fin2 - inicio2));

        //StringBuffer (Similar a StringBuilder pero sincronizado -> seguro en hilos)
        StringBuffer sbf = new StringBuffer("Thread-Safe");
        sbf.append("pero mas lento");
        System.out.println("Uso de StringBuffer: "+ sbf);

        //ejercicio String Pool y Heap
        //declaracion de Strings
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        String s4 = new String("Java");
        String s5 = new String("Java").intern();

        //comparaciones con ==
        System.out.println("s1 == s2 ->" +(s1 == s2) ); // Pool
        System.out.println("s1 == s3 ->" +(s1 == s3) ); // Heap vs Pool
        System.out.println("s3 == s4 ->" +(s3 == s4) ); //Heap distintos
        System.out.println("s1 == s5 ->" +(s1 == s5) ); // Pool despues de intern

        //comparaciones con equals()
        System.out.println("s1.equals(s2) -> "+ s1.equals(s2));
        System.out.println("s1.equals(s3) -> "+ s1.equals(s3));
        System.out.println("s3.equals(s4) -> "+ s3.equals(s4));
        System.out.println("s1.equals(s5) -> "+ s1.equals(s5));

        //Reasigancion y concatenacion
        s3 = s3 + "Programming"; //crea un nuevo objeto
        System.out.println("\nDespues de concatenar s3: "+s3);
        System.out.println("s3 == s4 -> "+(s3 == s4));//ahora son distintos

        //Internacion despues de concatnacion
        String s6 = s3.intern();
        System.out.println("s6 (intern de s3) -> "+s6);
        System.out.println("s6 == s1 -> "+(s6 == s1)); //false porque el contenido es diferente
    }
}
