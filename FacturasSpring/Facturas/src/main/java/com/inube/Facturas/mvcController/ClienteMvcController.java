package com.inube.Facturas.mvcController;


import com.inube.Facturas.model.Cliente;
import com.inube.Facturas.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller indica que esta clase pertenece a la capa web/MVC
//Se usa para devolver vistas (HTML) con ModelAndView o model
@Controller

//Prefijo comun para todas las rutas del controlador
//Ejemplo: /web/clientes
@RequestMapping("/web/clientes")
public class ClienteMvcController {

    //Inyeccion del servicio donde esta la logica de negocio
    private final ClienteService clienteService;

    //Inyeccion por constructor(Recomendada).
    public ClienteMvcController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    //Listado de cliente

    //GET /web/clientes}
    //Lista todos los clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("filtro", "todos"); //ayuda a mostrar el estado del filtro en la vista
        return "clientes/list"; //retorna la vista list.html
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("clientes", clienteService.findActivos());
        model.addAttribute("filtro", "activos"); //ayuda a mostrar el estado del filtro en la vista
        return "clientes/list";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("clientes", clienteService.findInactivos());
        model.addAttribute("filtro", "inactivos"); //ayuda a mostrar el estado del filtro en la vista
        return "clientes/list";
    }


    //FORMULARIO PARA REGISTRO NUEVO/EDITAR
    //GET /web/clientes/new
    //GET /web/clientes/edit/{id}

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormularioCliente(@PathVariable(required = false) Long id, Model model) {
        Cliente cliente;
        if (id != null) {
            //Si viene ID -> editar
            cliente = clienteService.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el cliente con el id: " + id));
            model.addAttribute("action", "edit");
        }else  {
            //Si no viene ID -> crear nuevo
            cliente = new Cliente();
            cliente.setActivo(1);
            model.addAttribute("action", "new");
        }
        model.addAttribute("cliente", cliente);
        return "clientes/form"; // muestra form.html
    }

    //Guardar cliente (CREATE/UPDATE)

    //POST /web/clientes
    //Spring llena automaticamente el objeto cliente con los valores del formulario.
    @PostMapping
    public String saveCliente(@ModelAttribute Cliente cliente) {
        clienteService.save(cliente);
        //despues de guardar redirige al listado principal
        return "redirect:/web/clientes";
    }

    //Detalles de un cliente

    //GET /web/clientes/{id}
    @GetMapping("/{id}")
    public String showDetallesCliennte(@PathVariable Long id, Model model) {
        //Usa el metodo que carga tambien los telefonos (JOIN FETCH)

        Cliente cliente = clienteService.findByIdWithPhones(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
        model.addAttribute("cliente", cliente);
        return "clientes/show"; //muestra  show.html
    }

    //Activar / deactivar cliente

    //GET /web/clientes/inactivar/{id}
    @GetMapping("/inactivar/{id}")
    public String inactivarCliente(@PathVariable Long id) {
        clienteService.inactivar(id);
        return "redirect:/web/clientes";
    }

    //GET /web/clientes/activar/{id}
    @GetMapping("/activar/{id}")
    public String ActivarCliente(@PathVariable Long id) {
        clienteService.activar(id);
        return "redirect:/web/clientes";
    }

}
