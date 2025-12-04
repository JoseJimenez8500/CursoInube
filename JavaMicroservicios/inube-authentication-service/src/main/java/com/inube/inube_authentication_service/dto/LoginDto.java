package com.inube.inube_authentication_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
    @JsonProperty("usuario")
    @NotBlank String nombreUsuario,

    @JsonProperty("password")
    @NotBlank String passwordUsuario
    ) {
}
