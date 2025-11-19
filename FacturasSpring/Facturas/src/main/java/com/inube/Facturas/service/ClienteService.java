package com.inube.Facturas.service;

import com.inube.Facturas.model.Cliente;
import com.inube.Facturas.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    //Dependencia del repositorio que maneja la BD
    private final ClienteRepository clienteRepository;

    //Inyeccion de dependencias por constructor (buena practica)
    //Spring inyecta automaticamente un ClienteRepository cuando crea el servicio
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Obtiene todos los clientes de la base de datos.
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    //Regresa unicamente clientes activos (ACTIVO = 1)
    public List<Cliente> findActivos() {
        return clienteRepository.findByActivo(1);
    }

    //Regresa unicamente clientes inactivos (ACTIVO = 0)
    public List<Cliente> findInactivos() {
        return clienteRepository.findByActivo(0);
    }

    //Busca un cliente por ID usando el metodo basico de JpaaRepository
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    //Igual que finById, pero cargando tambien la lista telefonos
    public Optional<Cliente> findByIdWithPhones(Long id) {
        return clienteRepository.findByIdWithPhones(id);
    }

    //Guarda o actualiza un cliente
    //si el ID es null -> crea uno nuevo
    //si el ID existe -> actualiza el registro
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //En lugar de borrar registros fisicamente, se "desactiva" el cliente
    //Esto es una buena practica llamada "borrado logico"
    public void inactivar(Long id){
        //Busca el cliente; si no lo encuentra, lanza excepcion.
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a inactivo
        cliente.setActivo(0);

        //Guarda el cambio en la BD
        clienteRepository.save(cliente);
    }

    //Activa un cliente previamente desactivado
    public void activar(Long id){
        //Busca el cliente; si no lo encuentra, lanza excepcion.
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido: " + id));

        //Cambia el estado a activo
        cliente.setActivo(1);

        //Guarda el cambio en la BD
        clienteRepository.save(cliente);
    }

}
