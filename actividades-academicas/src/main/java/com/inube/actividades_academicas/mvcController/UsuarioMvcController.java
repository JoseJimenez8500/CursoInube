package com.inube.actividades_academicas.mvcController;


import com.inube.actividades_academicas.model.Usuario;
import com.inube.actividades_academicas.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller indica que esta clase pertenece a la capa web/MVC
//Se usa para devolver vistas (HTML) con ModelAndView o model
@Controller

//Prefijo comun para todas las rutas del controlador
//Ejemplo: /web/usuario
@RequestMapping("/web/usuario")
public class UsuarioMvcController {

    //Inyeccion del servicio donde esta la logica de negocio
    private final UsuarioService usuarioService;

    //Inyeccion por constructor(Recomendada).
    public UsuarioMvcController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }


    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("filtro", "todos"); //ayuda a mostrar el estado del filtro en la vista
        return "usuario/list"; //retorna la vista list.html
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("usuarios", usuarioService.findEstatusActivo());
        model.addAttribute("filtro", "activos"); //ayuda a mostrar el estado del filtro en la vista
        return "usuario/list";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("usuarios", usuarioService.findEstatusInactivo());
        model.addAttribute("filtro", "inactivos"); //ayuda a mostrar el estado del filtro en la vista
        return "usuario/list";
    }


    //FORMULARIO PARA REGISTRO NUEVO/EDITAR
    //GET /web/clientes/new
    //GET /web/clientes/edit/{id}

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormularioUsuario(@PathVariable(required = false) Long id, Model model) {
        Usuario usuario;
        if (id != null) {
            //Si viene ID -> editar
            usuario = usuarioService.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el usuario con el id: " + id));
            model.addAttribute("action", "edit");
        }else  {
            //Si no viene ID -> crear nuevo
            usuario = new Usuario();
            usuario.setEstatus("Activo");
            model.addAttribute("action", "new");
        }
        model.addAttribute("usuario", usuario);
        return "usuario/form"; // muestra form.html
    }

    //Guardar usuario (CREATE/UPDATE)

    //POST /web/usuario
    //Spring llena automaticamente el objeto usuario con los valores del formulario.
    @PostMapping
    public String saveUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        //despues de guardar redirige al listado principal
        return "redirect:/web/usuario";
    }

    //Detalles de un usuario

    //GET /web/clientes/{id}
    @GetMapping("/{id}")
    public String showDetallesUsuario(@PathVariable Long id, Model model) {
        //Usa el metodo que carga tambien los telefonos (JOIN FETCH)

        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
        model.addAttribute("usuario", usuario);
        return "usuario/show"; //muestra  show.html
    }

    //Activar / deactivar cliente

    //GET /web/clientes/inactivar/{id}
    @GetMapping("/inactivar/{id}")
    public String inactivarUsuario(@PathVariable Long id) {
        usuarioService.inactivar(id);
        return "redirect:/web/usuario";
    }

    //GET /web/clientes/activar/{id}
    @GetMapping("/activar/{id}")
    public String ActivarUsuaio(@PathVariable Long id) {
        usuarioService.activar(id);
        return "redirect:/web/usuario";
    }

}
