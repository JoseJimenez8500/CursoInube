public class Condicionales {
    public static void main(String[] args) {
        //Ejemplos de condicionales

        //if
        int edad = 20;
        if(edad>=18){
            System.out.println("eres mayor de edad");
        }

        //If-else
        if(edad>=20){
            System.out.println("eres mayor de edad");
        }
        else {
            System.out.println("eres menor de edad");
        }

        //operador ternario
        String mensaje = (edad >= 18)? "Eres mayor de edad":"Eres menor de edad";
        System.out.println(mensaje);

        int numero = -5;

        String tipo = (numero >= 0) ? "positivo":"negativo";
        System.out.println("El numero es: "+tipo);

        //if - else if - else
        int nota = 75;
        if(nota>=90){
            System.out.println("excelente");
        } else if (nota>=70) {
            System.out.println("Aprobado");
        } else{
            System.out.println("No aprobado");
        }

        //switch
        //Desde java 14 se pueden usar switch con flechas (->), que son mas limpios
        int dia = 3;
        //mala implementacion
        if (dia == 1){
            System.out.println("Lunes");
        }
        if (dia == 2){
            System.out.println("Martes");
        }
        if (dia == 3){
            System.out.println("Miercoles");
        }
        if (dia == 4){
            System.out.println("Jueves");
        }
        if (dia == 5){
            System.out.println("Viernes");
        }else {
            System.out.println("Fin de semana");
        }
        //Optimizacion
        switch(dia){
            case 1 -> System.out.println("Domingo");
            case 2 -> System.out.println("Martes");
            case 3 -> System.out.println("Miercoles");
            case 4 -> System.out.println("Jueves");
            case 5 -> System.out.println("Viernes");
            default -> System.out.println("Fin de semana");
        }

        //Buenas practicas
        //Condiciones claras y expresivas

        int x = 1;
        int y = 2;
        int z = 3;
        int w = 4;

        if(x==1 && y!=2 || z==3 && w<4){
            //Codigo
        }

        //mejor
        boolean cumpleCondicion = (x==1 && y!=2 || z==3 && w<4);
        if(cumpleCondicion){
            System.out.println("Cumple condicion");
        }

        //Evitar anidaciones profundas
        //dificil de leer
        boolean tieneIdentificacion = false;
        if(edad>=18){
            if(tieneIdentificacion){
                System.out.println("Puede entrar");
            }
        }

        //mejor
        if(edad>=18 && tieneIdentificacion){
            System.out.println("Puede entrar");
        }

        /**
         *  CONSIDERACIONES
         *  usar switch en lugar de muchos else if
         *  el operador ternario solo en casos simples
         *  si la logica es mas compleja, es mejor usar else if para legibilidad
         */
    }
}
