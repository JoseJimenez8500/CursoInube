package com.inube.inube_authentication_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import net.jcip.annotations.Immutable;

import java.io.Serial;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Immutable
@Table(name = "viw_login", schema = "public")
public class ViwLogin {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid_usuario")
    @JsonProperty("uuidUsuario")
    private UUID uuidUsuario;

    @Column(name = "usuario")
    @JsonProperty("usuario")
    private String usuario;

    @Column(name = "id")
    @JsonProperty("id")
    private UUID id;

    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "firstname")
    @JsonProperty("firstname")
    private String firstname;

    @Column(name = "lastname")
    @JsonProperty("lastname")
    private String lastname;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "token")
    @JsonProperty("token")
    private String token;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "avatar")
    @JsonProperty("avatar")
    private String avatar;

    @Column(name = "location")
    @JsonProperty("location")
    private String location;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Transient
    @JsonProperty("sucursales")
    private List<ViwUsuarioSucursal> sucursales;
}

