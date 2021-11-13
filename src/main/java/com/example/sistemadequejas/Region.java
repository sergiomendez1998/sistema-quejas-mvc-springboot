package com.example.sistemadequejas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "region")
@EqualsAndHashCode(of = "idRegion", callSuper = false)
@ToString(of = "idRegion")
@DynamicInsert
@DynamicUpdate
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long idRegion;


    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<Usuario> usuarioSet = new HashSet<>();

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<Punto> puntoSet = new HashSet<>();

}
