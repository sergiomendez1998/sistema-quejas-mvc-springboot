package com.example.sistemadequejas.dao;

import com.example.sistemadequejas.IngresoQuejas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoQuejaDAO extends JpaRepository<IngresoQuejas, Long> {
}
