package com.example.sistemadequejas.service.usuario;

import com.example.sistemadequejas.Usuario;

import javax.management.relation.Role;
import java.util.List;

public interface UsuarioService {
    public List<Usuario> listaUsuarios();
    public void addUsuario(Usuario usuario);
    Usuario encontrarUsuarioPorDpi(String dpi);
    Usuario econtrarUsuarioPorId(Usuario usuario);
    public void removeUsuario(Usuario usuario);
    public List<Usuario> getUsuarioByRole(String role);
}
