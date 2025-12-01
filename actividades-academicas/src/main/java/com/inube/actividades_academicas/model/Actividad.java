package com.inube.actividades_academicas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {
    @Id
    //Configuracion para bases de datos Oracle (secuencia)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actividad_seq")
    @SequenceGenerator(name= "actividad_seq", sequenceName = "SEC_ID_ACTIVIDAD", allocationSize = 1)
    @Column(name= "ID_ACTIVIDAD", nullable = false)
    private Long idActividad; //Usamos Long para el Id de tipo NUMBER

    //Relacion con la tabla Usuario
    //Muchas actividades pueden pertenecer a un usuario (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false,
        referencedColumnName = "ID_USUARIO",
        foreignKey = @ForeignKey(name= "FK_ID_USUARIO_ACTIVIDAD"))
    private Usuario usuario; // Asume que tienes una entidad Usuario

    @ManyToOne
    @JoinColumn(name = "ID_SALA", nullable = false,
            referencedColumnName = "ID_SALA",
            foreignKey = @ForeignKey(name= "FK_ID_SALA_ACTIVIDAD"))
    private Sala sala; // Asume que tienes una entidad Sala

    @Column(name = "NOMBRE", nullable = false, length = 40)
    private String nombre; //VARCHAR2(15) se mapea a String

    @Column(name = "HO_ENTRADA", nullable = false)
    private LocalDateTime hoEntrada;

    @Column(name = "HO_SALIDA", nullable = false)
    private LocalDateTime hoSalida;

    @Column(name="ESTATUS", nullable = false)
    private String estatus = "Activo";

    @Column(name="FECHA_ALTA")
    private LocalDate fechaAlta = LocalDate.now();
}
