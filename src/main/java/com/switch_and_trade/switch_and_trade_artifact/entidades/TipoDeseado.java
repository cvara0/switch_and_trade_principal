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
@Table(name="tipo_deseado")
@SQLDelete(sql = "UPDATE tipo_deseado SET eliminado_tipo_deseado = true WHERE id = ?")
public class TipoDeseado {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id_tipo_deseado")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_propiedad_tipo_deseado", referencedColumnName = "id_tipo_propiedad")
    private TipoPropiedad tipoPropiedad;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_vehiculo_tipo_deseado", referencedColumnName = "id_tipo_vehiculo")
    private TipoVehiculo tipoVehiculo;

    @Column(name = "eliminado_tipo_deseado", nullable = false)
    private Boolean eliminado;

}
