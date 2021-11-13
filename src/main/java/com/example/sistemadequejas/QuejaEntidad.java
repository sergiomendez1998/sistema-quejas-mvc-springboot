package com.example.sistemadequejas;

import javassist.bytecode.ByteArray;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@MappedSuperclass
public class QuejaEntidad implements Serializable {


    @Column(name = "estado_externo")
    private String estadaExterno;

    @Column(name = "estado_interno")
    private String estadoInterno;

    @Column(name = "ingreso_queja")
    private String  ingresoQueja;

    @Column(name = "correlativo")
    private String correlativo;


    @Column(name = "creado_el")
    private LocalDateTime creadoEL;

    @Column(name = "modificado_el")
    private LocalDateTime modificadoEl;


    @PrePersist
    public void ejecutarAntesPersistir(){
        int i=10;
        this.creadoEL = LocalDateTime.now();
        this.estadaExterno = "presentado";
        this.estadoInterno = "presentado";
        this.ingresoQueja = "Menu Aplicacion";//Portal CuentaHabiente

    }

    @PreUpdate
    public  void ejecutarAntesDeActulizar(){
        this.modificadoEl = LocalDateTime.now();
    }




}
