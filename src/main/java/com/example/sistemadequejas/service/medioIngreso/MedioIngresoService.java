package com.example.sistemadequejas.service.medioIngreso;

import com.example.sistemadequejas.MedioIngreso;

import java.util.List;

public interface MedioIngresoService  {
    public List<MedioIngreso> listaMediosIngresos();
    public void guardar(MedioIngreso medioIngreso);
}
