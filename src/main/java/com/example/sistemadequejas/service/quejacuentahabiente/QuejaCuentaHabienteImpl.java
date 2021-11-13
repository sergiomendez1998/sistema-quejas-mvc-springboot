package com.example.sistemadequejas.service.quejacuentahabiente;

import com.example.sistemadequejas.QuejaCuentaHabiente;
import com.example.sistemadequejas.dao.QuejaCuentaHabienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuejaCuentaHabienteImpl implements  QuejaCuentaHabienteService{

    @Autowired
    private QuejaCuentaHabienteDAO quejaCuentaHabienteDAO;

    @Override
    public List<QuejaCuentaHabiente> listaQuejaCuentaHabiente() {

        return (List<QuejaCuentaHabiente>) quejaCuentaHabienteDAO.findAll();
    }

    @Override
    public void guardar(QuejaCuentaHabiente quejaCuentaHabiente) {
        quejaCuentaHabienteDAO.save(quejaCuentaHabiente);
    }
}
