package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente guardar(Cliente c) {
        return repo.save(c);
    }

    public Cliente obtenerPorId(Long id) {
        Optional<Cliente> cliente = repo.findById(id);
        return cliente.orElse(null);
    }

    public Cliente actualizar(Long id, Cliente datos) {
        Cliente c = obtenerPorId(id);
        if (c == null) return null;

        c.setNombre(datos.getNombre());
        c.setApellido(datos.getApellido());
        c.setTelefono(datos.getTelefono());
        c.setCorreo(datos.getCorreo());

        return repo.save(c);
    }

    public boolean eliminar(Long id) {
        Cliente c = obtenerPorId(id);
        if (c == null) return false;
        repo.delete(c);
        return true;
    }
}
