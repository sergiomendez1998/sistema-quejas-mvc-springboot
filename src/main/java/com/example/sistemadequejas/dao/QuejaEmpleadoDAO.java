package com.example.sistemadequejas.dao;

import com.example.sistemadequejas.Cargo;
import com.example.sistemadequejas.QuejaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejaEmpleadoDAO extends JpaRepository<QuejaEmpleado, Long> {
}
