package com.inube.actividades_academicas.mvcController;

import com.inube.actividades_academicas.model.Actividad;
import com.inube.actividades_academicas.service.ActividadService;
import com.inube.actividades_academicas.service.SalaService;
import com.inube.actividades_academicas.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/actividad")
public class ActividadMvcController {
    private ActividadService actividadService;
    private UsuarioService usuarioService;
    private SalaService salaService;


    public ActividadMvcController(ActividadService actividadService, UsuarioService usuarioService, SalaService salaService) {
        this.actividadService = actividadService;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
    }

    @GetMapping
    public String listarActividad(Model model) {
        model.addAttribute("actividades", actividadService.findAll());
        model.addAttribute("filtro", "todos");
        return "actividad/list";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("actividades", actividadService.findActivos());
        model.addAttribute("filtro", "activos"); //ayuda a mostrar el estado del filtro en la vista
        return "actividad/list";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("actividades", actividadService.findInactivo());
        model.addAttribute("filtro", "inactivos"); //ayuda a mostrar el estado del filtro en la vista
        return "actividad/list";
    }

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormularioActividad(@PathVariable(required = false) Long id, Model model) {
        Actividad  actividad;
        if (id != null) {
            //Si viene ID -> editar
            actividad = actividadService.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el cliente con el id: " + id));
            model.addAttribute("action", "edit");
        }else {
            //Si no viene ID -> crear nuevo
            actividad = new Actividad();
            actividad.setEstatus("Activo");
            model.addAttribute("action", "new");
            model.addAttribute("activosUsuarios", usuarioService.findEstatusActivo());
            model.addAttribute("activosSalas", salaService.findEstatusActivo());
        }
        model.addAttribute("actividad", actividad);
        return "actividad/form"; // muestra form.html
    }

    @PostMapping
    public String saveActividad(@ModelAttribute Actividad actividad) {
        actividadService.save(actividad);
        //despues de guardar redirige al listado principal
        return "redirect:/web/actividad";
    }

    @GetMapping("/inactivar/{id}")
    public String inactivarActividad(@PathVariable Long id) {
        actividadService.inactivar(id);
        return "redirect:/web/actividad";
    }

    @GetMapping("/activar/{id}")
    public String activarActividad(@PathVariable Long id) {
        actividadService.Activar(id);
        return "redirect:/web/actividad";
    }
}
