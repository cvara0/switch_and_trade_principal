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
@Table(name = "publicacion", indexes = {@Index(name = "idx_id_tipo_publicacion", columnList = "id_tipo_publicacion")})
@SQLDelete(sql = "UPDATE publicacion SET eliminado_publicacion = true WHERE id_publicacion = ?")
public class Publicacion {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column(name="id_publicacion")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_publicacion", referencedColumnName = "id_tipo",nullable = false)
    private Tipo tipo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil",nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_provincia_publicacion", referencedColumnName = "id_provincia")
    private Provincia provincia;

    @Column(name="foto_publicacion")
    private String foto;

    @Column(name = "descripcion_corta_publicacion",length = 50,nullable = false)
    private String descripcionCorta;

    @Column(name="descripcion_publicacion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "localidad_publicacion",length = 60,nullable = false)
    private String localidad;

    @Column(name = "tipo_deseado_1_publicacion", nullable = false)
    private String tipoDeseado1;

    @Column(name = "tipo_deseado_2_publicacion")
    private String tipoDeseado2;

    @Column(name = "eliminado_publicacion", nullable = false)
    private Boolean eliminado;

   // los que quieren lo que yo tengo
}
