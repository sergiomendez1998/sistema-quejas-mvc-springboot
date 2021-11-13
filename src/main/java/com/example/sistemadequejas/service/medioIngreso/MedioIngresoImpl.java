package com.example.sistemadequejas.service.medioIngreso;

import com.example.sistemadequejas.MedioIngreso;
import com.example.sistemadequejas.dao.MedioIngresoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioIngresoImpl implements MedioIngresoService {

 @Autowired
 private MedioIngresoDAO medioIngresoDAO;

    @Override
    public List<MedioIngreso> listaMediosIngresos() {
       return (List<MedioIngreso>) medioIngresoDAO.findAll();
    }

    @Override
    public void guardar(MedioIngreso medioIngreso) {

      medioIngresoDAO.save(medioIngreso);
    }
}
