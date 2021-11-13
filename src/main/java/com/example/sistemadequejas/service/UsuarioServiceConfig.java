package com.example.sistemadequejas.service;


import com.example.sistemadequejas.Rol;
import com.example.sistemadequejas.Usuario;
import com.example.sistemadequejas.dao.UsuarioDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioServiceConfig implements UserDetailsService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {

        Usuario usuario = usuarioDAO.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }
        //agregamos en una lista los roles que existen de ese usuario
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRolSet()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //returnamos el usuario y sus roles
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }



}
