package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Registrar un nuevo usuario
    public Usuario registrar(String nombre, String apellido, String username, String clave) {
        // Verificar si ya existe un usuario con el mismo username
        if (usuarioRepository.findByUsername(username) != null) {
            return null; // ya existe
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setUsername(username);
        nuevo.setClave(clave);

        return usuarioRepository.save(nuevo);
    }

    // Iniciar sesión: ahora devuelve el usuario si es válido
    public Usuario iniciarSesion(String username, String clave) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario != null && usuario.getClave().equals(clave)) {
            return usuario;
        }

        return null;
    }

    // Buscar usuario por username
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
