package com.example.sistemadequejas.service.cargo;

import com.example.sistemadequejas.Cargo;
import com.example.sistemadequejas.dao.CargoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoSevice {

    @Autowired
    private CargoDAO cargoDAO;

    @Override
    public List<Cargo> cargoList() {


        return (List<Cargo>) cargoDAO.findAll();
    }
}
