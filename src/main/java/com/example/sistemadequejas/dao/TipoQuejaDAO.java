package com.example.sistemadequejas.dao;


import com.example.sistemadequejas.TipoQueja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoQuejaDAO extends JpaRepository<TipoQueja, Long> {
}
