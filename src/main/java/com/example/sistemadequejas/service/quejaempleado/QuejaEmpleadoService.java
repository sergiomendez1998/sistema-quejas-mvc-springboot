package com.example.sistemadequejas.service.quejaempleado;

import com.example.sistemadequejas.QuejaCuentaHabiente;
import com.example.sistemadequejas.QuejaEmpleado;

import java.util.List;

public interface QuejaEmpleadoService {

    public List<QuejaEmpleado> listaQuejaEmpleados();
    public void guardar(QuejaEmpleado quejaempleado);
}
