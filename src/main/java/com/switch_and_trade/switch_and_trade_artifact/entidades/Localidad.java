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
@Table(name="localidad")
@SQLDelete(sql = "UPDATE localidad JOIN provincia ON localidad.id_provincia_localidad=provincia.id_provincia SET eliminado_localidad = true WHERE id = ? OR provincia.eliminado_provincia=true")//probar
public class Localidad {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_localidad")
    private Long id;

    @Column(name="nombre_localidad")
    private String nombre;

    @Column(name = "eliminado_localidad", nullable = false)
    private Boolean eliminado;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_provincia_localidad", referencedColumnName = "id_provincia")
    private Provincia provincia;
}
