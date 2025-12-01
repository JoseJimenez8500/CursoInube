package com.inube.actividades_academicas.mvcController;

import com.inube.actividades_academicas.model.Sala;
import com.inube.actividades_academicas.model.Usuario;
import com.inube.actividades_academicas.service.SalaService;
import com.inube.actividades_academicas.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

//Prefijo comun para todas las rutas del controlador
//Ejemplo: /web/usuario
@RequestMapping("/web/sala")
public class SalaMvcController {

    //Inyeccion del servicio donde esta la logica de negocio
    private final SalaService salaService;

    //Inyeccion por constructor(Recomendada).
    public SalaMvcController(SalaService salaService) {
        this.salaService = salaService;
    }


    @GetMapping
    public String listarSalas(Model model) {
        model.addAttribute("salas", salaService.findAll());
        model.addAttribute("filtro", "todos"); //ayuda a mostrar el estado del filtro en la vista
        return "sala/list"; //retorna la vista list.html
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("salas", salaService.findEstatusActivo());
        model.addAttribute("filtro", "activos"); //ayuda a mostrar el estado del filtro en la vista
        return "sala/list";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("salas", salaService.findEstatusInactivo());
        model.addAttribute("filtro", "inactivos"); //ayuda a mostrar el estado del filtro en la vista
        return "sala/list";
    }


    //FORMULARIO PARA REGISTRO NUEVO/EDITAR
    //GET /web/clientes/new
    //GET /web/clientes/edit/{id}

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormularioUsuario(@PathVariable(required = false) Long id, Model model) {
        Sala sala;
        if (id != null) {
            //Si viene ID -> editar
            sala = salaService.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe sala con el id: " + id));
            model.addAttribute("action", "edit");
        }else  {
            //Si no viene ID -> crear nuevo
            sala = new Sala();
            sala.setEstatus("Activo");
            model.addAttribute("action", "new");
        }
        model.addAttribute("sala", sala);
        return "sala/form"; // muestra form.html
    }

    //Guardar usuario (CREATE/UPDATE)

    //POST /web/sala
    //Spring llena automaticamente el objeto sala con los valores del formulario.
    @PostMapping
    public String saveSala(@ModelAttribute Sala sala) {
        salaService.save(sala);
        //despues de guardar redirige al listado principal
        return "redirect:/web/sala";
    }

    //Detalles de un usuario

    //GET /web/clientes/{id}
    @GetMapping("/{id}")
    public String showDetallesUsuario(@PathVariable Long id, Model model) {
        //Usa el metodo que carga tambien los telefonos (JOIN FETCH)

        Sala sala = salaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
        model.addAttribute("sala", sala);
        return "sala/show"; //muestra  show.html
    }

    //Activar / desactivar sala

    @GetMapping("/inactivar/{id}")
    public String inactivarSala(@PathVariable Long id) {
        salaService.inactivar(id);
        return "redirect:/web/sala";
    }

    @GetMapping("/activar/{id}")
    public String ActivarSala(@PathVariable Long id) {
        salaService.activar(id);
        return "redirect:/web/sala";
    }
}
