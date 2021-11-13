package com.example.sistemadequejas;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "puntos")
@EqualsAndHashCode(of = "idPunto", callSuper = false)
@ToString(of = "idPunto")
@DynamicInsert
@DynamicUpdate
public class Punto extends Entidad{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punto")
    private Long idPunto;

    @NotEmpty
    @Column(name = "nombre")
    private String nombre;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    private Region region;

    @OneToMany(mappedBy = "punto", fetch = FetchType.EAGER)
    private Set<Usuario> usuarioSet = new HashSet<>();




}
