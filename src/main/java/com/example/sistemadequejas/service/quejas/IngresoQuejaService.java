package com.example.sistemadequejas.service.quejas;

import com.example.sistemadequejas.IngresoQuejas;

import java.util.List;

public interface IngresoQuejaService {


    public List<IngresoQuejas> listaQuejas();
    public List<IngresoQuejas> listaQuejasCuentaHabiente();
    public void guardar(IngresoQuejas IngresoQuejas);
    IngresoQuejas EncontrarPorCorrelativo(String correlativo);
}
