package com.switch_and_trade.switch_and_trade_artifact.entidades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Table(name="provincia")
@SQLDelete(sql = "UPDATE provincia SET eliminado_provincia = true WHERE id = ?")
public class Provincia {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_provincia")
    private Long id;

    @Column(name="nombre_provincia")
    private String nombre;

    @Column(name = "eliminado_provincia", nullable = false)
    private Boolean eliminado;
}
