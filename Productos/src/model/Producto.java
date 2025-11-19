package model;
import java.sql.Date;
import java.util.UUID;

//Definicion de la clase Producto
public class Producto {

    //Atributos privados (Encapsulados) de la clase
    private String idProducto;
    private String nombreProducto;
    private double precio;
    private Date fechaCreacion;

    /**
     * Constructor de la clase Producto
     * este metodo se ejecuta al crear un nuevo producto
     * @param nombreProducto
     * @param precio
     * @param fechaCreacion
     */


    //genera automaticamente un UUID al crear el producto
     public Producto(String nombreProducto, double precio, Date fechaCreacion) {
         //Genera un identificador unico (UUID) de manera automatica
         this.idProducto = UUID.randomUUID().toString();
         this.nombreProducto = nombreProducto;
         this.precio = precio;
         this.fechaCreacion = fechaCreacion;
     }

    public Producto(String idProducto, String nombreProducto, double precio, Date fechaCreacion) {
        //Genera un identificador unico (UUID) de manera automatica
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto='" + idProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
