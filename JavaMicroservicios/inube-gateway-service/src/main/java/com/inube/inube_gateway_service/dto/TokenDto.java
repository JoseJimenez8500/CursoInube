package com.inube.inube_gateway_service.dto;

// Un record en Java se usa para representar datos de forma simple e inmutable.
// Este TokenDto se usa normalmente para devolver el token (JWT) al cliente
// después de que inicia sesión o se autentica.
public record TokenDto(
String token // El token JWT generado por el servidor no usages
) {
// No hay necesidad de agregar código adicional.
// Java crea automáticamente el constructor, getter,
// equals(), hashCode() y toString().
}