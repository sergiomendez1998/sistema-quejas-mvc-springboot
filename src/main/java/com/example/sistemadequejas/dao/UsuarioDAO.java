package com.example.sistemadequejas.dao;


import com.example.sistemadequejas.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);


}
