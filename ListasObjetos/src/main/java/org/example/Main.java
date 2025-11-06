package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Ejemplo de Listas con objetos

        //Crear lista de clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Carlos", 30,"Ciudad de Mexico"));
        clientes.add(new Cliente("Ana", 25,"Guadalajara"));
        clientes.add(new Cliente("Pedro", 40,"Monterrey"));
        clientes.add(new Cliente("Lucia", 35,"Guadalajara"));
        clientes.add(new Cliente("Andres", 28,"Ciudad de Mexico"));

        System.out.println("Lista de clientes: ");
        clientes.forEach(System.out::println);

        //filtrar clientes por ciudad
        System.out.println("\nClientes de Guadalajara: ");
        clientes.stream()
                .filter(c -> c.getCiudad().equals("Guadalajara"))
                .forEach(System.out::println);

        //Ordenar clientes por edad
        System.out.println("\nClientes ordenados por edad (menor a mayor)");
        clientes.stream()
                //.sorted(Comparator.comparingInt(c->c.getEdad))
                .filter(c -> c.getCiudad().equals("Guadalajara"))
                .forEach(System.out::println);

        //Obtener solo nombres en una nueva lista
        List<String> nombres = clientes.stream()
                .map(Cliente::getNombre)
                .toList();
        System.out.println("\nLista de nombres: "+nombres);

        //Buscar el cliente mas joven
        clientes.stream()
                .min(Comparator.comparingInt(Cliente::getEdad))
                .ifPresent(c -> System.out.println("Cliente mas joven"+c));

        //Buscar el cliente mas grande
        clientes.stream()
                .max(Comparator.comparingInt(Cliente::getEdad))
                .ifPresent(c -> System.out.println("Cliente con mas edad"+c));

        //Contar cuantos clientes son de CDMX
        long cdmxCount = clientes.stream()
                .filter(c -> c.getCiudad().equals("Ciudad de Mexico"))
                .count();
        System.out.println("\nNumero de clientes de CDMX: "+cdmxCount);

        //Crear lista inmutable
        List<Cliente> clientesInmutables = List.copyOf(clientes);
        System.out.println("\nLista inmutable creada con List.copyOf()");

    }
}