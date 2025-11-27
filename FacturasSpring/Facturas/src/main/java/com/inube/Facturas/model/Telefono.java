package com.inube.Facturas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TELEFONOS")
public class Telefono {
    @Id
    //Configuracion para bases de datos Oracle (secuencia)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefono_seq")
    @SequenceGenerator(name= "telefono_seq", sequenceName = "SEC_ID_TELEFONO", allocationSize = 1)
    @Column(name= "ID_TELEFONO", nullable = false)
    private Long idTelefono; //Usamos Long para el Id de tipo NUMBER

    //Relacion con la tabla CLIENTES
    //Muchos telefonos pueden pertenecer a un cliente (ManyToOne)
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE", nullable = false,
        referencedColumnName = "ID_CLIENTE",
        foreignKey = @jakarta.persistence.ForeignKey(name= "FK_ID_CLIENTE_TELEFONOS"))
    private Cliente cliente; // Asume que tienes una entidad Cliente

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono; //VARCHAR2(15) se mapea a String

    @Column(name="ACTIVO", nullable = false)
    private Integer activo = 1;
}
