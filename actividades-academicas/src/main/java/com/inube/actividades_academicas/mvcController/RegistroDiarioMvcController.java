package com.inube.actividades_academicas.mvcController;

import com.inube.actividades_academicas.service.RegistroDirarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/registroDirario")
public class RegistroDiarioMvcController {

    private final RegistroDirarioService registroDirarioService;
    public RegistroDiarioMvcController(RegistroDirarioService registroDirarioService) {
        this.registroDirarioService = registroDirarioService;
    }

    @GetMapping
    public String listarRegistroDiarios(Model model) {
        model.addAttribute("registrosDiarios", registroDirarioService.findAll());
        model.addAttribute("filtro", "todos"); //ayuda a mostrar el estado del filtro en la vista
        return "registroDiario/list"; //retorna la vista list.html
    }
}
