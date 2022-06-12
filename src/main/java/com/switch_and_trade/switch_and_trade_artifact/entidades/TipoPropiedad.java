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
@Table(name="tipo_propiedad")
@SQLDelete(sql = "UPDATE tipo_propiedad SET eliminado_tipo_propiedad = true WHERE id = ?")
public class TipoPropiedad {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_tipo_propiedad")
    private Long id;

    @Column(name="nombre_tipo_propiedad")
    private String nombre;

    @Column(name = "eliminado_tipo_propiedad", nullable = false)
    private Boolean eliminado;

}
