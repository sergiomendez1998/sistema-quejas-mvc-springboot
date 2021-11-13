package com.example.sistemadequejas;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "idUsuario", callSuper = false)
@ToString(of = "idUsuario")
@DynamicInsert
@DynamicUpdate
public class Usuario extends Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;


    @Length(max = 15)
    @NotEmpty
    @Column(name = "dpi")
    private String dpi;

    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @Email
    @Column(name = "correo")
    private String email;

    @NotEmpty
    @Column(name = "username")
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo", referencedColumnName="id_cargo")
    private Cargo cargo;

    @OneToMany(mappedBy="usuario")
    private Set<Rol> rolSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_punto", referencedColumnName = "id_punto")
    private Punto punto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    private Estado estado;


}
