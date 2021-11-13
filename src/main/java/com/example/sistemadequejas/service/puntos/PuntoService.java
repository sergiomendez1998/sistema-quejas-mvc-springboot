package com.example.sistemadequejas.service.puntos;

import com.example.sistemadequejas.Punto;


import java.util.List;
import java.util.Optional;

public interface PuntoService {

    public List<Punto> listaPuntos();
    public void guardar(Punto punto);
    public void eliminar(Punto punto);
    Punto encontrarPunto(Punto punto);
    public List<Punto> buscaPuntosPorRegion(Long id);

}
