import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Clase principal del proyecto que muestra el uso de tipos avanzados en java
 * @version 1.0
 * @autor Eduardo
 */
public class TiposAvanzados {
    /**
     * Metodo principal de la aplicacion
     * @params args - Argumentos de linea de comandos (no se usa en esta aplicacion
     */
    public static void main(String[] args) {
        //Ejemplo BigDecimal, BigInteger, LocalDate y localDateTime

        //BigDecimal
        System.out.println("BigDecimal (finanzas):");
        BigDecimal precio = new BigDecimal("2500.75");
        BigDecimal iva = new BigDecimal("0.16");
        BigDecimal descuento = new BigDecimal("0.10");

        //calcular IVA y descuento
        BigDecimal conIva = precio.add(precio.multiply(iva));
        BigDecimal conDescuento = conIva.subtract(conIva.multiply(descuento));

        System.out.println("Precio base: "+precio);
        System.out.println("Con Iva (16%): "+conIva);
        System.out.println("Con Descuento (10%): "+conDescuento);

        //Interes compuesto: montoFinal = monto * (1 + tasa)^años
        BigDecimal monto = new BigDecimal("10000");
        BigDecimal tasa = new BigDecimal("0.05");
        int anios = 10;
        BigDecimal montoFinal = monto.multiply(BigDecimal.ONE.add(tasa)).pow(anios);

        System.out.println("Interes compuesto (10 años, 5%): "+montoFinal+"\n");

        //BigInteger
        System.out.println("BigInteger (Numeros enormes)");
        int n = 30;//factorial de 30
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        System.out.println("Factorial de " + n + ": "+factorial);

        //localDate
        System.out.println("LocalDate (fechas):");
        LocalDate hoy = LocalDate.now();
        LocalDate navidad = LocalDate.of(2025, 12,25);//yyyy-mm-dd

        Period periodo = Period.between(hoy, navidad);
        System.out.println("Hoy: "+hoy);
        System.out.println("Navidad: "+navidad);
        System.out.println("Faltan: "+periodo.getMonths()+" meses y " + periodo.getDays()+" dias\n");

        //LocalDateTime
        System.out.println("LocalDateTime (Fecha y hora)");
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime evento = LocalDateTime.of(2025, 10,10,15,30);//yyyy-mm-dd hh:30

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Ahora: "+ahora.format(formato));
        System.out.println("Evento: "+evento.format(formato));

        Duration duracion = Duration.between(ahora, evento);
        System.out.println("Faltan "+ duracion.toHours() + " horas ("+duracion.toDays()+" dias)");


    }
}
