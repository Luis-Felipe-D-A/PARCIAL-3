package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("mensaje", "");
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String clave,
                                Model model) {

        Usuario usuario = usuarioService.iniciarSesion(username, clave);

        if (usuario == null) {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "login";
        }

        // Enviar usuario al home
        model.addAttribute("usuario", usuario);
        return "home";
    }

    // Mostrar registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("mensaje", "");
        return "registro";
    }

    // Procesar registro
   // Procesar registro
    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                @RequestParam String apellido,
                                @RequestParam String username,
                                @RequestParam String clave,
                                Model model) {

        Usuario nuevo = usuarioService.registrar(nombre, apellido, username, clave);

        if (nuevo == null) {
            model.addAttribute("mensaje", "El username ya está en uso");
            return "registro"; // Se queda en el registro si falla
        }

        // Si se registró correctamente, redirige al login
        model.addAttribute("mensaje", "Registro exitoso. Por favor inicia sesión.");
        return "redirect:/usuarios/login";
    }

}
