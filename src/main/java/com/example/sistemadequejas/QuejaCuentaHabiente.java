package com.example.sistemadequejas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@Table(name = "queja_cuentahabiente")
@EqualsAndHashCode(of = "idQueja", callSuper = false)
@ToString(of = "idQueja")
@DynamicInsert
@DynamicUpdate
public class QuejaCuentaHabiente extends QuejaCuentaHEntidad{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queja")
    private Long idQueja;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "oficina")
    private String oficina;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;


    @Column(name = "detalla_queja")
    private String detalleQueja;


    @Lob
    @Column(name = "file")
    private byte[] pdfFile;



    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoQueja tipoQueja;


}
