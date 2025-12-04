package com.inube.inube_gateway_service.dto;

// Un record en Java es una clase inmutable usada para transportar datos (DTO).
// Java automáticamente genera:
// - Constructor
// - Getters
// - equals(), hashCode()
// - toString()
// Todo sin que tengas que escribirlo.
//
// Este record representa un DTO que contiene:
// - la URI de la petición
// - el método HTTP usado (GET, POST, etc.)
public record RequestDto(
    String uri,     // Dirección o endpoint solicitado  no usages
    String method   // Método HTTP (GET, POST, PUT, DELETE...) no usages
) {
    // El bloque está vacío porque no se necesita lógica extra.
    // Java ya genera todo automáticamente.
}