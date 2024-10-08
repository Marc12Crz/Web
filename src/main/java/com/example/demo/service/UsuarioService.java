package com.example.demo.service;

import com.example.demo.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(Long id);
    void eliminarUsuario(Long id);
}
