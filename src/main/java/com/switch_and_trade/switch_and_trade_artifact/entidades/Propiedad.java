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
@SQLDelete(sql = "UPDATE propiedad SET eliminado_propiedad = true WHERE id_propiedad = ?")
public class Propiedad {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column(name="id_propiedad")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_propiedad_propiedad", referencedColumnName = "id_tipo_propiedad",nullable = false)
    private TipoPropiedad tipoPropiedad;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_propiedad", referencedColumnName = "id_perfil",nullable = false)
    private Perfil perfil;


    @Column(name="foto_propiedad")
    private String foto;

    @Column(name = "descripcion_corta_propiedad",length = 50,nullable = false)
    private String descripcionCorta;

    @Column(name="descripcion_propiedad", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name="superficie_propiedad", nullable = false)
    private Integer superficie;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_provincia_propiedad", referencedColumnName = "id_provincia",nullable = false)
    private Provincia provincia;

    @Column(name = "localidad_propiedad",length = 60,nullable = false)
    private String localidad;

    @Column(name = "tipo_deseado_1_propiedad", nullable = false)
    private String tipoDeseado1;

    @Column(name = "tipo_deseado_2_propiedad")
    private String tipoDeseado2;

    @Column(name = "eliminado_propiedad", nullable = false)
    private Boolean eliminado;

}
