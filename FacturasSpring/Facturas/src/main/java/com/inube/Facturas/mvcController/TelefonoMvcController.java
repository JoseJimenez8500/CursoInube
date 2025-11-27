package com.inube.Facturas.mvcController;

import com.inube.Facturas.model.Cliente;
import com.inube.Facturas.model.Telefono;
import com.inube.Facturas.service.ClienteService;
import com.inube.Facturas.service.TelefonoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/telefonos")
public class TelefonoMvcController {
    private TelefonoService telefonoService;
    private ClienteService clienteService;

    public TelefonoMvcController(TelefonoService telefonoService, ClienteService clienteService) {
        this.telefonoService = telefonoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarTelefonos(Model model) {
        model.addAttribute("telefonos", telefonoService.findAll());
        model.addAttribute("filtro", "todos");
        return "telefonos/listTelefonos";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("telefonos", telefonoService.findActivos());
        model.addAttribute("filtro", "activos"); //ayuda a mostrar el estado del filtro en la vista
        return "telefonos/listTelefonos";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("telefonos", telefonoService.findInactivo());
        model.addAttribute("filtro", "inactivos"); //ayuda a mostrar el estado del filtro en la vista
        return "telefonos/listTelefonos";
    }

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormularioTelefono(@PathVariable(required = false) Long id, Model model) {
        Telefono  telefono;
        if (id != null) {
            //Si viene ID -> editar
            telefono = telefonoService.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el cliente con el id: " + id));
            model.addAttribute("action", "edit");
        }else  {
            //Si no viene ID -> crear nuevo
            telefono = new Telefono();
            telefono.setActivo(1);
            model.addAttribute("action", "new");
            model.addAttribute("activos", clienteService.findActivos());
        }
        model.addAttribute("telefono", telefono);
        return "telefonos/form"; // muestra form.html
    }

    @PostMapping
    public String saveTelefono(@ModelAttribute Telefono telefono) {
        telefonoService.save(telefono);
        //despues de guardar redirige al listado principal
        return "redirect:/web/telefonos";
    }

    @GetMapping("/{id}")
    public String showDetallesTelefono(@PathVariable Long id, Model model) {
        //Usa el metodo que carga tambien los clientes (JOIN FETCH)

        Telefono telefono = telefonoService.findByIdWithClientes(id)
                .orElseThrow(() -> new IllegalArgumentException("Telefono no encontrado: " + id));
        model.addAttribute("telefono", telefono);
        return "telefonos/show"; //muestra  show.html
    }

    @GetMapping("/inactivar/{id}")
    public String inactivarTelefono(@PathVariable Long id) {
        telefonoService.inactivar(id);
        return "redirect:/web/telefonos";
    }

    @GetMapping("/activar/{id}")
    public String activarTelefono(@PathVariable Long id) {
        telefonoService.Activar(id);
        return "redirect:/web/telefonos";
    }
}
