package com.inube.inube_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Accessors(chain = true)
@SQLRestriction("estatus_registro <> 99")
@Table(name = "aba_clientes", schema = "public")
public class AbaCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_cliente")
    @JsonProperty("uuidCliente")
    private UUID uuidCliente;

    @Column(name = "uuid_ciudad")
    @JsonProperty("uuidCiudad")
    private UUID uuidCiudad;

    @Column(name = "uuid_estado")
    @JsonProperty("uuidEstado")
    private UUID uuidEstado;

    @Column(name = "uuid_pais")
    @JsonProperty("uuidPais")
    private UUID uuidPais;

    @Column(name = "uuid_regimen")
    @JsonProperty("uuidRegimen")
    private UUID uuidRegimen;

    @Column(name = "uuid_uso_cfdi")
    @JsonProperty("uuidUsoCfdi")
    private UUID uuidUsoCfdi;

    @Column(name = "uuid_estatus_cliente")
    @JsonProperty("uuidEstatusCliente")
    private UUID uuidEstatusCliente;

    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String nombre;

    @Column(name = "apaterno")
    @JsonProperty("apellidoPaterno")
    private String aPaterno;

    @Column(name = "amaterno")
    @JsonProperty("aMaterno")
    private String aMaterno;

    @Column(name = "razon_social")
    @JsonProperty("razonSocial")
    private String razonSocial;

    @Column(name = "rfc")
    @JsonProperty("rfc")
    private String rfc;

    @Column(name = "nif")
    @JsonProperty("nif")
    private String nif;

    @Column(name = "calle")
    @JsonProperty("calle")
    private String calle;

    @Column(name = "numero")
    @JsonProperty("numero")
    private String numero;

    @Column(name = "colonia")
    @JsonProperty("colonia")
    private String colonia;

    @Column(name = "codigp_postal")
    @JsonProperty("codigoPostal")
    private Integer codigoPostal;

    @Column(name = "telefono")
    @JsonProperty("telefono")
    private String telefono;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "observaciones")
    @JsonProperty("observaciones")
    private String observaciones;

    @Column(name = "estatus_registro")
    @JsonProperty("estatusRegisto")
    private Short estatusRegisto;

    @Column(name = "fecha_creacion")
    @JsonProperty("fechaCreacion")
    private Timestamp fechaCreacion;

    @Column(name = "uuid_usuario_creacion")
    @JsonProperty("uuidUsuarioCreacion")
    private UUID uuidUsuarioCreacion;

    @Column(name = "fecha_modificacion")
    @JsonProperty("fechaModificacion")
    private Timestamp fechaModificacion;

    @Column(name = "uuid_usuario_modificacion")
    @JsonProperty("uuidUsuarioModificacion")
    private UUID uuidUsuarioModificacion;

    @Column(name = "obs_registro")
    @JsonProperty("obsRegistro")
    private String obsRegistro;
}