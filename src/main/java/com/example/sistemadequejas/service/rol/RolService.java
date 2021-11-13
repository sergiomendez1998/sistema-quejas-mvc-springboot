package com.example.sistemadequejas.service.rol;

import com.example.sistemadequejas.Rol;
import com.example.sistemadequejas.Usuario;

import java.util.List;

public interface RolService {
    public List<Rol> listaRoles();
    public void create(Rol rol);
}
