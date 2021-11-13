package com.example.sistemadequejas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cargo")
@EqualsAndHashCode(of = "idCargo", callSuper = false)
@ToString(of = "idCargo")
@DynamicInsert
@DynamicUpdate
public class Cargo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long idCargo;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(mappedBy="cargo")
   private Usuario Usuario;
}
