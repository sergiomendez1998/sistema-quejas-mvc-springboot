package com.example.sistemadequejas.service.quejacuentahabiente;

import com.example.sistemadequejas.Punto;
import com.example.sistemadequejas.QuejaCuentaHabiente;

import java.util.List;

public interface QuejaCuentaHabienteService {

    public List<QuejaCuentaHabiente> listaQuejaCuentaHabiente();
    public void guardar(QuejaCuentaHabiente quejaCuentaHabiente);

}
