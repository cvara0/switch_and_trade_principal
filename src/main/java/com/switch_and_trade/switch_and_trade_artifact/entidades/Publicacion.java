package com.switch_and_trade.switch_and_trade_artifact.entidades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Table(name="publicacion")
@SQLDelete(sql = "UPDATE publicacion SET eliminado_publicacion = true WHERE id = ?")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_publicacion")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil",nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)//no se puede tener el mismo vechiculo en 2 publicaciones
    @JoinColumn(name = "id_vehiculo_publicacion", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_propiedad_publicacion", referencedColumnName = "id_propiedad")
    private Propiedad propiedad;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_provincia_publicacion", referencedColumnName = "id_provincia")
    private Provincia provincia;

    @Column(name = "localidad_publicacion",length = 60,nullable = false)
    private String localidad;

    @Column(name = "tipo_deseado_1_publicacion", nullable = false)
    private String tipoDeseado1;

    @Column(name = "tipo_deseado_2_publicacion")
    private String tipoDeseado2;

    @Column(name = "tipo_deseado_3_publicacion")
    private String tipoDeseado3;

    @Column(name = "eliminado_publicacion", nullable = false)
    private Boolean eliminado;

    
    //fecha
}
