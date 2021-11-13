package com.example.sistemadequejas.service.regiones;

import com.example.sistemadequejas.Region;
import com.example.sistemadequejas.dao.RegionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{

    @Autowired
    private RegionDAO regionDAO;

    @Override
    public List<Region> listaRegions() {

        return (List<Region>) regionDAO.findAll();
    }
}
