package com.example.sistemadequejas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "estado")
@EqualsAndHashCode(of = "idEstado", callSuper = false)
@ToString(of = "idEstado")
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(name = "nombre")
    private String nombre;
}
