package com.example.sistemadequejas.service.rol;


import com.example.sistemadequejas.Rol;
import com.example.sistemadequejas.dao.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDAO rolDAO;


    @Override
    public List<Rol> listaRoles() {
        return (List<Rol>) rolDAO.findAll();
    }

    @Override
    public void create(Rol rol) {
        rolDAO.save(rol);
    }
}
