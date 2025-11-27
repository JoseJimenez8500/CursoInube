package com.inube.Facturas.controller;

import com.inube.Facturas.model.Cliente;
import com.inube.Facturas.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints relacionados con la entidad Cliente.
 * Forma parte de la API desarrollada para el equipo de Inube.
 wwwwwwww
 * Sirve como capa de entrada que recibe solicitudes HTTP y delega la lógica
 * en la capa de servicio.
 */
@RestController
@RequestMapping("/api/clientes") // Ruta base del recurso
public class ClienteController {

    private final ClienteService clienteService;
    /**
     * Inyección de dependencias mediante constructor.
     *
     * Es la mejor práctica en Spring porque:
     * - - Hace la clase inmutable.
     * - Facilita pruebas unitarias y de integración.
     * - Evita problemas de proxies o instancias nulas.
     */
    public ClienteController (ClienteService clienteService) {
        this.clienteService = clienteService;
    }
// 1. CONSULTAR TODOS LOS CLIENTES (READ ALL)

    /**
     * Endpoint para obtener la lista completa de clientes.
     * GET /api/clientes/all
     * Retorna una lista con todos los registros de la tabla CLIENTE.
     */
    @GetMapping("/all")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll(); // Llama a la capa de servicio
    }

// 2. CONSULTAR CLIENTE POR ID (READ BY ID)
    /**
     * Endpoint para buscar un cliente por su ID.
     * GET /api/clientes/{id}
     *
     *@PathVariable Long id Extrae el valor del ID desde la URL.
     * Si el cliente existe retorna 200 OK con el cliente.
     * Si no existe retorna 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
            .map(ResponseEntity::ok) // Si lo encuentra → 200 OK
            .orElseGet(() -> ResponseEntity.notFound().build()); // Si no lo encuentra 404

    }
//
// 3. CREAR NUEVO CLIENTE (CREATE)

/**
 * Endpoint para crear un nuevo cliente.
 * POST /api/clientes
 * NOTA IMPORTANTE:
 * - EL JSON recibido NO debe incluir "idCliente", ya que este se genera automáticamente.
 *
 *@ResponseStatus(HttpStatus.CREATED) → Retorna 201 Created como indica la convención REST.
 */
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public Cliente createCliente (@RequestBody Cliente cliente) {
         return clienteService.save(cliente);
     }

 // 4. ACTUALIZAR CLIENTE EXISTENTE (UPDATE)
 //
 /**
  * Endpoint para actualizar un cliente existente.
  * PUT /api/clientes/{id}
  * El ID del cliente a actualizar se obtiene desde la URL.
  * El cuerpo de la petición (JSON) debe contener los nuevos valores.
  * Flujo:
  *1. Busca el cliente por ID.
  *2. Si existe:
  *Se actualizan únicamente los campos permitidos.
  *Se guarda el registro.
  *Retorna 200 OK.
  *3. Si no existe:
  *Retorna 404 Not Found.*/

     @PutMapping("/{id}")
     public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {

         return clienteService.findById(id)
                 .map(clienteExistente -> {
                     // Actualización de valores permitidos
                     clienteExistente.setNombre(clienteDetails.getNombre());
                     clienteExistente.setApellidoPaterno(clienteDetails.getApellidoPaterno());
                     clienteExistente.setApellidoMaterno(clienteDetails.getApellidoMaterno());
                     clienteExistente.setRfc(clienteDetails.getRfc());
                     clienteExistente.setActivo(clienteDetails.getActivo());
                     // Guardar cambios en la base
                     Cliente updatedCliente = clienteService.save(clienteExistente);
                     return ResponseEntity.ok(updatedCliente); // Retorna 200 OK
                 })
                 .orElseGet(() -> ResponseEntity.notFound().build()); // Si no existe -> 404 Not Found
     }

}
