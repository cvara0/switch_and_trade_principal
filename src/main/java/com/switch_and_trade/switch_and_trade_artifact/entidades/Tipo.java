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
@Table(name="tipo",indexes = {@Index(name = "idx_nombre_tipo", columnList = "nombre_tipo")})
@SQLDelete(sql = "UPDATE tipo SET eliminado_tipo = true WHERE id_tipo = ?")
public class Tipo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_tipo")
    private Long id;

    @Column(name="nombre_tipo", length = 60, nullable = false)
    private String nombre;

    @Column(name = "eliminado_tipo", nullable = false)
    private Boolean eliminado;

}
