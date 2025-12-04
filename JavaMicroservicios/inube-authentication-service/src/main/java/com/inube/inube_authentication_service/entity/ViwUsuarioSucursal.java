package com.inube.inube_authentication_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import net.jcip.annotations.Immutable;

import java.io.Serial;
import java.util.UUID;

@Data
@Entity
@Immutable
@Table(name= "viw_usuario_sucursal", schema = "public")
public class ViwUsuarioSucursal {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid_sucursal")
    @JsonProperty("uuidSucursal")
    private UUID uuidSucursal;

    @Column(name = "uuid_usuario")
    @JsonProperty("uuidUsuario")
    private UUID uuidUsuario;

    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String nombre;
}