package com.switch_and_trade.switch_and_trade_artifact.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "perfil", indexes = {@Index(name = "idx_email_perfil", columnList = "email_perfil")})
@SQLDelete(sql = "UPDATE perfil SET eliminado_perfil = true WHERE id = ?")
public class Perfil {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_perfil")
    private Long id;

    @Column(name = "email_perfil", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "clave_perfil", nullable = false)
    private String clave;

    @Column(name = "nombre_perfil",length = 50,nullable = false)
    private String nombre;

    @Column(name = "apellido_perfil",length = 50,nullable = false)
    private String apellido;

    @Column(name = "telefono_perfil",nullable = false)
    private Long telefono;

    //@Column(name="foto_perfil")
    //private String foto;

    //@ManyToOne(fetch = EAGER)
    //@JoinColumn(name="id_localidad_perfil", referencedColumnName = "id_localidad")
    //private Localidad localidad;

    @Column(name = "eliminado_perfil", nullable = false)
    private Boolean eliminado=false;

    @Enumerated(STRING)
    @Column(name = "rol_perfil", nullable = false)
    private Rol rol;
}
