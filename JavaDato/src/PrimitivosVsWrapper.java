public class PrimitivosVsWrapper {

    public static void main(String[] args) {
        //Tipos de datos primitivos vs Wrappers en Java

        //declaracion basica
        int primitivoInt = 10;
        Integer wrapperInt = 10;

        System.out.println("int primitivoInt = "+primitivoInt);
        System.out.println("Integer wrapperInt = "+wrapperInt);

        //autoboxing y unboxing
        Integer autoBox = primitivoInt; //autoboxing
        int unBox = wrapperInt;         //unboxing
        System.out.println("Autoboxing int -> Integer = "+autoBox);
        System.out.println("unboxing Integer -> int = "+unBox);

        // Comparaciones
        int a = 100;
        int b = 100;
        Integer x = 166;
        Integer y = 100;
        Integer z = 200;

        System.out.println("Comparaciones");
        System.out.println("a==b ->"+ (a==b));              //true (primitivos comparan valor)
        System.out.println("x==y ->"+ (x==y));              //true (cache -128 a 127)
        System.out.println("x==z ->"+ (x==z));              //false
        System.out.println("x.equals(y)->"+ (x.equals(y))); //true (compara valor contenido)
        System.out.println("x.equals(z)->"+ (x.equals(z))); //false

        //operaciones aritmeticas

        int sumaPrimitivos = a+b;
        Integer sumaWrapper = x+y; //unboxing automatico al operar
        System.out.println("sumaPrimitivos (a+b) = "+sumaPrimitivos);
        System.out.println("sumaWrapper (x+y) = "+sumaWrapper);

        //conversion de String a numero
        String numeroTexto= "123";
        int numPrimitivos = Integer.parseInt(numeroTexto);
        Integer numWrapper = Integer.valueOf(numeroTexto);

        System.out.println("conversion de String");
        System.out.println("Integer.parseInt(\"123\") -> " +numPrimitivos);
        System.out.println("Integer.valueOf(\"123\") -> "+ numWrapper);

        //Uso en colecciones (Solo wrappers funcionan en colecciones genericas)
        java.util.List<Integer> lista = new java.util.ArrayList<>();
        lista.add(20);
        lista.add(20);
        lista.add(30);

        System.out.println("\nlista con wrappers");
        for (Integer n : lista) {
            System.out.println("Elemento: "+n);
        }

        //Nullability (Solo wrappers pueden ser null)

        Integer puedeSerNull = null;
        System.out.println("Wrappers con null permitido:");
        System.out.println("Integer puede ser null = "+puedeSerNull);

        //int primitivoNull = puedeSerNull;
    }
}
