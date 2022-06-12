package com.switch_and_trade.switch_and_trade_artifact.entidades;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vehiculo", indexes = {@Index(name = "idx_id_tipo_vehiculo_vehiculo", columnList = "id_tipo_vehiculo_vehiculo")})
@SQLDelete(sql = "UPDATE vehiculo SET eliminado_vehiculo = true WHERE id = ?")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;

    @Column(name = "anio_vehiculo", nullable = false)
    private short anio;

    @Column(name = "marca_vehiculo", length = 60, nullable = false)
    private String marca;

    @Column(name = "modelo_vehiculo", length = 60, nullable = false)
    private String modelo;

    @Column(name = "descripcion_vehiculo", columnDefinition = "TEXT", nullable = true)
    private String descripcion;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_vehiculo_vehiculo", referencedColumnName = "id_tipo_vehiculo")
    private TipoVehiculo tipoVehiculo;//preguntarse que es lo que se comparte entre entidades

    @Column(name="foto_vehiculo")
    private String foto;

    @Column(name = "deseado_vehiculo", nullable = false)
    private Boolean deseado;

    @Column(name = "eliminado_vehiculo", nullable = false)
    private Boolean eliminado;





}
