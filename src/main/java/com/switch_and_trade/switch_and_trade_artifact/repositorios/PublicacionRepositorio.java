package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {


    @Query(value = "SELECT * " +
            "FROM " +
                "publicacion " +
            "JOIN " +
                "tipo_deseado " +
            "ON " +
                "publicacion.id_tipo_deseado_publicacion=tipo_deseado.id_tipo_deseado " +
            "JOIN " +
                "tipo_propiedad " +
            "ON " +
                "tipo_deseado.id_tipo_propiedad_tipo_deseado=tipo_propiedad.id_tipo_propiedad " +
            "JOIN " +
                "tipo_vehiculo " +
            "ON " +
                "tipo_deseado.id_tipo_vehiculo_tipo_deseado=tipo_vehiculo.id_tipo_vehiculo " +
            "JOIN " +
                "vehiculo " +
            "ON " +
                "vehiculo.id_tipo_vehiculo_vehiculo=tipo_vehiculo.id_tipo_vehiculo " +
            "JOIN " +
                "propiedad " +
            "ON " +
                "propiedad.id_tipo_propiedad_propiedad=tipo_propiedad.id_tipo_propiedad " +
            "WHERE " +
                "publicacion.id_publicacion=?1" +
            "AND" +
                "tipo_vehiculo.nombre_tipo_vehiculo " +
            "IN " +
                "(SELECT " +
                    "tipo_vehiculo.nombre_tipo_vehiculo " +
                "FROM " +
                    "tipo_vehiculo  " +
                "JOIN " +
                    "vehiculo " +
                "ON " +
                    " vehiculo.id_tipo_vehiculo_vehiculo=tipo_vehiculo.id_tipo_vehiculo" +
                "WHERE " +
                    "vehiculo.deseado_vehiculo=0)" +
            "OR" +
                "tipo_vehiculo.nombre_tipo_vehiculo " +
            "IN " +
                "(SELECT " +
                    "tipo_propiedad.nombre_tipo_propiedad " +
                "FROM " +
                    "tipo_propiedad  " +
                "JOIN " +
                    "propiedad " +
                "ON " +
                    " propiedad.id_tipo_propiedad_propiedad=tipo_propiedad.id_tipo_propiedad" +
                "WHERE " +
                    "propiedad.deseado_propiedad=0)"
                ,nativeQuery = true)
    List<Publicacion> traerPublicacionesQueOfrecenAlgunoDeMisDeseos(Long idPublicacion);
    /*de la publicacion extraigo los tipos deseados, los cuales estan vinculados a los tipos deseados de vehiculo
    y propiedad, ahora debo comparar dichos tipos deseados con los demas en la bd y que a su vez sus propiedades
    y vehiculos correspondientes tengan deseo en false. y el id de la publicacion sera pasado como parametro
    *
    *
    * */
    /*
    traer publicaciones, que tengan deseado de vehiculo false, y cuyo tipo, coincida con mi tipo de
    vehiculo deseado


    //List<Publicacion> traerPublicacionesQueDeseanMiPropiedad(Publicacion miPublicacion);
    //List<Publicacion> traerPublicacionesQueOfrecenMiPropiedadDeseada(Publicacion miPublicacion);



/*
    List<Publicacion> traerTodoOrdenNombrePerfilAsc();

    List<Publicacion> traerTodoOrdenTipoVehiculoAsc();

    List<Publicacion> traerTodoOrdenTipoPropiedadAsc();


    @Column(name="id_publicacion")

    @Column(name = "eliminado_publicacion", nullable = false)

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_perfil_publicacion", referencedColumnName = "id_perfil", nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_vehiculo_publicacion", referencedColumnName = "id_vehiculo", nullable = true)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_propiedad_publicacion", referencedColumnName = "id_propiedad", nullable = true)
    private Propiedad propiedad;

    */
     /*si la propiedad de la publicacion es ofrecida
            y el tipo de la propiedad de la publicacion coincide con el tipo de todas las propiedades deseadas
            y alguna propiedad de todas las publicaciones es deseada
            y el id del perfil de la publicacion es diferente al id de algun perfil de todas las publicaciones
            entonces tengo todas las publicaciones que desean mi propiedad
             */
}
