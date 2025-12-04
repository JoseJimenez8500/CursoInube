package com.inube.inube_authentication_service.dto;

public record ResponseDto(
    Boolean succes,
    String mensaje,
    String error,
    Object data
    ) {}
