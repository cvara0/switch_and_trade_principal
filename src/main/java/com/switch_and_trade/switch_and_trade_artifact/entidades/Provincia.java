package com.switch_and_trade.switch_and_trade_artifact.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "provincia", indexes = {@Index(name = "idx_nombre_provincia", columnList = "nombre_provincia")})
@SQLDelete(sql = "UPDATE provincia SET eliminado_provincia = true WHERE id_provincia = ?")
public class Provincia {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_provincia")
    private Long id;

    @Column(name = "nombre_provincia", length = 60, nullable = false)
    private String nombre;

    @Column(name = "eliminado_provincia", nullable = false)
    private Boolean eliminado;
}
/*
* "Buenos Aires",
            "Catamarca",
            "Chaco",
            "Chubut",
            "Córdoba",
            "Corrientes",
            "Entre Ríos",
            "Formosa",
            "Jujuy",
            "La Pampa",
            "La Rioja",
            "Mendoza",
            "Misiones",
            "Neuquén",
            "Río Negro",
            "Salta",
            "San Juan",
            "San Luis",
            "Santa Cruz",
            "Santa Fe",
            "Santiago del Estero",
            "Tierra del Fuego, Antártida e Islas del Atlántico Sur",
            "Tucumán"
* */