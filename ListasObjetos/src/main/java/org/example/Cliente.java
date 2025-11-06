package org.example;

public class Cliente {

    //propiedades
    private String nombre;
    private int edad;
    private String ciudad;

    //Constructor
    public Cliente(String nombre, int edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    //Sobreescribir toString para imprimir bien el objeto

    @Override
    public String toString() {
        return nombre + " (" + edad + " años " + ciudad +" )";

    }
}
