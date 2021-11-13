package com.example.sistemadequejas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "queja_empleado")
@EqualsAndHashCode(of = "idQueja", callSuper = false)
@ToString(of = "idQueja")
@DynamicInsert
@DynamicUpdate
public class QuejaEmpleado extends QuejaEntidad {

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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medio")
    private MedioIngreso medioIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private TipoQueja tipoQueja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;




}
