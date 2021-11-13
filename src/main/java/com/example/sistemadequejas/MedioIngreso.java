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
@Table(name = "medio_ingreso")
@EqualsAndHashCode(of = "idMedio", callSuper = false)
@ToString(of = "idMedio")
@DynamicInsert
@DynamicUpdate
public class MedioIngreso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_medio")
    private Long idMedio;

    @Column(name = "nombre")
    private String nombre;
}
