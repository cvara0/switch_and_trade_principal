package com.switch_and_trade.switch_and_trade_artifact.entidades;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "propiedad", indexes = {@Index(name = "idx_id_tipo_propiedad_propiedad", columnList = "id_tipo_propiedad_propiedad")})
@SQLDelete(sql = "UPDATE propiedad SET eliminado_propiedad = true WHERE id = ?")
public class Propiedad {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column(name="id_propiedad")
    private Long id;

    @Column(name="superficie_propiedad", nullable = false)
    private Integer superficie;

    @Column(name="descripcion_propiedad", columnDefinition = "TEXT", nullable = true)
    private String descripcion;

    @Column(name="foto_propiedad")
    private String foto;

    @Column(name = "deseado_propiedad", nullable = false)
    private Boolean deseado;

    @Column(name = "eliminado_propiedad", nullable = false)
    private Boolean eliminado;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_propiedad_propiedad", referencedColumnName = "id_tipo_propiedad")
    private TipoPropiedad tipoPropiedad;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_provincia_propiedad", referencedColumnName = "id_provincia")
    private Provincia provincia;



}
