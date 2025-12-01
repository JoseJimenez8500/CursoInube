package com.inube.actividades_academicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
@Table(name="SALA")
public class Sala {

    //Indica que este atributo es la llave primaria
    @Id

    //Configuracion para que el Id se genere mediante una secuencia (Oracle)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sala_seq")

    //Define la secuencia de Oracle que usara JPA para generar los IDs
    @SequenceGenerator(
            name="sala_seq",
            sequenceName = "SEC_ID_SALA",
            allocationSize = 1
    )

    //Mapea la columna ID, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="ID_SALA", nullable = false)
    private Long idSala; //NUMBER en Oracle -> Long en java

    //Mapea la columna Nombre, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="NOMBRE", nullable = false, length = 40)
    private String nombre;

    @Column(name="DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name="ESTATUS", length = 10)
    private String estatus = "Activo";

    @Column(name="FECHA_ALTA")
    private LocalDate fechaAlta = LocalDate.now();

    //Relacion uno a muchos con la entidad Telefono:
    //-mappedBy = "cliente": indica que la FK esta en la tabla TELEFONOS.
    //-cacade = ALL: al guardar o borrar un cliente, sus telefonos tambien.
    //-fetch = LAZY: solo carga los telefonos cuando se necesitan.
    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Actividad> actividad;
}
