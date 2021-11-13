package com.example.sistemadequejas.service.quejaempleado;

import com.example.sistemadequejas.QuejaCuentaHabiente;
import com.example.sistemadequejas.QuejaEmpleado;
import com.example.sistemadequejas.dao.QuejaEmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuejaEmpleadoImpl implements  QuejaEmpleadoService{

    @Autowired
    private QuejaEmpleadoDAO quejaEmpleadoDAO;

    @Override
    public List<QuejaEmpleado> listaQuejaEmpleados() {
        return (List<QuejaEmpleado>) quejaEmpleadoDAO.findAll();
    }

    @Override
    public void guardar(QuejaEmpleado quejaempleado) {
                quejaEmpleadoDAO.save(quejaempleado);
    }
}
