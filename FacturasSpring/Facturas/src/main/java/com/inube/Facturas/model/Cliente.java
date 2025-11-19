package com.inube.Facturas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Data es una anotacion de Lombok que genera automaticamente:
 * -Getters y Setters
 * -toString
 * -equals() y hashCode()
 * -Ademas, evita escribir todo ese codigo maualmente
 */
@Data

//Genera un constructo vacio (sin parametros)
@NoArgsConstructor

//Genera un constructo con totos los paarametros
@AllArgsConstructor

//@Builder permite crear objetos usando el patron Builder.
//Facilita instanciar objetos de forma mas legible.
@Builder

//Indica que esta clase es una entidad JPA y se mapeara a una tabla de BD
@Entity
@Table(name="CLIENTES")
public class Cliente {

    //Indica que este atributo es la llave primaria
    @Id

    //Configuracion para que el Id se genere mediante una secuencia (Oracle)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cliente_seq")

    //Define la secuencia de Oracle que usara JPA para generar los IDs
    @SequenceGenerator(
            name="cliente_seq",
            sequenceName = "SEC_ID_CLIENTE",
            allocationSize = 1
    )

    //Mapea la columna Nombre, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="ID_CLIENTE", nullable = false)
    private Long idCliente; //NUMBER en Oracle -> Long en java

    //Mapea la columna Nombre, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="NOMBRE", nullable = false, length = 40)
    private String nombre;

    //Mapea la columna APATERNO, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="APATERNO", nullable = false, length = 40)
    private String apellidoPaterno;

    //Mapea la columna AMATERNO, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="AMATERNO", length = 40)
    private String apellidoMaterno;

    //Mapea la columna RFC, opcional con longitud maxima de 13 caracteres
    @Column(name="RFC", length = 13)
    private String rfc;

    //Mapea la columna FECHA_ALTA (DATE) y asigna la fecha actual por defecto
    @Column(name="FECHA_ALTA")
    private LocalDate fechaAlta = LocalDate.now();

    //Campo activo obligatorio 1 = activo, 0 = inactivo
    @Column(name="ACTIVO", nullable = false)
    private Integer activo = 1;

    //Relacion uno a muchos con la entidad Telefono:
    //-mappedBy = "cliente": indica que la FK esta en la tabla TELEFONOS.
    //-cacade = ALL: al guardar o borrar un cliente, sus telefonos tambien.
    //-fetch = LAZY: solo carga los telefonos cuando se necesitan.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefono> telefonos;




}
