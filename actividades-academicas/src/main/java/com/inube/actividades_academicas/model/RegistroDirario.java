package com.inube.actividades_academicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name="VW_REGISTRO_DIARIO")
public class RegistroDirario {
    //Indica que este atributo es la llave primaria
    @Id

    //Mapea la columna ID, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="ID_ACTIVIDAD", nullable = false)
    private Long idActividad; //NUMBER en Oracle -> Long en java

    //Mapea la columna Nombre, obligatoria (NOT NULL) con longitud maxima 40
    @Column(name="NOMBRE_USUARIO", nullable = false, length = 40)
    private String nombre;

    @Column(name="NOMBRE_ACTIVIDAD", nullable = false, length = 40)
    private String nombreActividad;

    @Column(name="NOMBRE_SALA", nullable = false, length = 40)
    private String nombreSala;

    @Column(name="HORARIO_ENTRADA", nullable = false)
    private String horarioEntrada;

    @Column(name="HORARIO_SALIDA", nullable = false)
    private String horarioSalida;
}
