public class DatosTipoWrapper {
    public static void main(String[] args){
        //tipos de datos wrappers en java

        //Byte
        Byte edad = 25;
        System.out.println("Byte edad: "+edad);
        System.out.println("Byte MAX_VALUE= "+ Byte.MAX_VALUE);

        //Short
        Short anio = 2025;
        System.out.println("Short anio: "+anio);
        System.out.println("Short MAX_VALUE= "+ Short.MIN_VALUE);

        //Integer
        Integer poblacionCiudad = 1_500_000;
        System.out.println("Integer poblacionCiudad: "+poblacionCiudad);
        System.out.println("Integer parseInt(\"123\") = "+ Integer.parseInt("123"));//String ->int
        System.out.println("Integer toHexString(255) = "+ Integer.toHexString(255));//Decimal ->Hex

        //Long
        Long poblacionMundial = 8_000_000_000L;
        System.out.println("Long poblacionMundial: "+poblacionMundial);
        System.out.println("Long compare(100L, 200L) = "+ Long.compare(100L, 200L));//-1

        //Float
        Float precio = 99.99f;
        System.out.println("Float precio: "+precio);
        System.out.println("Float isNaN(0.0f / 0.0f): "+Float.isNaN(0.0f / 0.0f));

        //Double
        Double pi = 3.1415;
        System.out.println("Double pi: "+pi);
        System.out.println("Double valueOf(\"2.718\": "+ Double.valueOf("2.718"));

        //Character
        Character inicial = 'C';
        System.out.println("Character inicial: "+inicial);
        System.out.println("Character.isLetter('9'): "+Character.isLetter('9'));
        System.out.println("Character.toLowerCase('A'): "+Character.toLowerCase('A'));

        //Boolean
        Boolean esJavaGenial = Boolean.TRUE;
        System.out.println("Boolean esJava: "+esJavaGenial);
        System.out.println("Boolean.parseBoolean(\"false\"): "+Boolean.parseBoolean("false"));

        // OPERACIONES CON WRAPPERS

        System.out.println("\n ===Operaciones con Wrappers");

        Integer x = 10;
        Integer y = 20;

        //Autoboxing unboxing
        int suma = x+y; //detras de camaras se desempaqueta(Unboxing)
        System.out.println("Suma (x+y): "+suma);

        //comparaciones
        System.out.println("x.compareTo(y): "+x.compareTo(y)); //-1
        System.out.println("x.equals(y): "+x.equals(y));

        //conversion de tipos
        String str = x.toString();
        System.out.println("x.toString(): "+str);

        int numero = Integer.parseInt("1234");
        System.out.println("Integer.parseInt(\"1234\"): "+numero);
    }
}
