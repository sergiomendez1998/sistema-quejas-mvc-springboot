package com.example.sistemadequejas;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.persistence.*;


@Data
@Entity
@Table(name = "tipo_queja")
@EqualsAndHashCode(of = "idTipo", callSuper = false)
@ToString(of = "idTipo")
public class TipoQueja extends Entidad{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @Column(name = "siglas")
    private String siglas;

    @Column(name = "descripcion")
    private String descripcion;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    private Estado estado;

    public TipoQueja() {
    }
}
