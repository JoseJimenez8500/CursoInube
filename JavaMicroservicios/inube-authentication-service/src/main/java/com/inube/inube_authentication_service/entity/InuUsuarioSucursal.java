package com.inube.inube_authentication_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Accessors(chain = true)
@SQLRestriction("estatus_registro <> 99")
@Table(name = "aba_usuario_sucursal", schema = "public")
public class InuUsuarioSucursal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_usuario_sucursal")
    @JsonProperty("uuidUsuarioSucursal")
    private UUID uuidUsuarioSucursal;

    @Column(name = "uuid_usuario")
    @JsonProperty("uuidUsuario")
    private UUID uuidUsuario;

    @Column(name = "uuid_sucursal")
    @JsonProperty("uuidSucursal")
    private UUID uuidSucursal;

    @Column(name = "estatus_registro")
    @JsonIgnore()
    private Integer estatusRegistro;

    @Column(name = "uuid_usuario_creacion")
    @JsonIgnore()
    private UUID uuidUsuarioCreacion;

    @Column(name = "fecha_creacion")
    @JsonIgnore()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @Column(name = "uuid_usuario_modificacion")
    @JsonIgnore()
    private UUID uuidUsuarioModificacion;

    @Column(name = "fechaModificacion")
    @JsonIgnore()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaModificacion;

    @Column(name = "obs_registro")
    @JsonIgnore()
    private UUID obsRegistro;
}