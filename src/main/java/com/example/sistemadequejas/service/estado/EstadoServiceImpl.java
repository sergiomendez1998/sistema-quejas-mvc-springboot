package com.example.sistemadequejas.service.estado;

import com.example.sistemadequejas.Estado;
import com.example.sistemadequejas.dao.EstadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements  EstadoService{

    @Autowired
    EstadoDAO estadoDAO;

    @Override
    public List<Estado> listaEstados() {
        return (List<Estado>) estadoDAO.findAll();
    }
}
