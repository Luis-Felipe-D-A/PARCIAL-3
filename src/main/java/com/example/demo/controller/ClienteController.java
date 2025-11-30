package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // Mostrar lista de clientes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", service.listar());
        return "clientes"; // clientes.html
    }

    // Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form"; // formulario
    }

    // Guardar nuevo cliente
    @PostMapping
    public String guardar(@ModelAttribute Cliente cliente) {
        service.guardar(cliente);
        return "redirect:/clientes";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.obtenerPorId(id));
        return "cliente-form";
    }

    // Actualizar cliente
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        service.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/clientes";
    }
}
