package com.switch_and_trade.switch_and_trade_artifact.entidades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import java.util.List;

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

    @Column(name = "eliminado_publicacion", nullable = false)
    private Boolean eliminado;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil", nullable = true)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)//no se puede tener el mismo vechiculo en 2 publicaciones
    @JoinColumn(name = "id_vehiculo_publicacion", referencedColumnName = "id_vehiculo", nullable = true)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_propiedad_publicacion", referencedColumnName = "id_propiedad", nullable = true)
    private Propiedad propiedad;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="id_tipo_deseado_publicacion", referencedColumnName = "id_tipo_deseado")
    private TipoDeseado tipoDeseado;

    //fecha
}
