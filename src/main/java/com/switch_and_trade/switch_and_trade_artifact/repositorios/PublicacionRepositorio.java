package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

    // inicio trae todo y ordena

    @Query(value = "SELECT * FROM publicacion WHERE eliminado_publicacion=1", nativeQuery = true)
    List<Publicacion> traerTodoEliminado();

    @Query(value = "SELECT * FROM publicacion WHERE eliminado_publicacion=0", nativeQuery = true)
    List<Publicacion> traerTodoNoEliminado();

    @Query(value = "SELECT * FROM publicacion JOIN perfil ON publicacion.id_perfil_publicacion=perfil.id_perfil WHERE perfil.id_perfil=?1 AND publicacion.eliminado_publicacion=0", nativeQuery = true)
    List<Publicacion> traerTodoNoEliminadoPorIdPerfil(Long idPerfil);

    @Query(value = "SELECT * FROM publicacion WHERE ((publicacion.tipo_deseado_1_publicacion IN (SELECT tipo.nombre_tipo FROM publicacion JOIN tipo ON publicacion.id_tipo_publicacion=tipo.id_tipo WHERE publicacion.id_publicacion=?1 AND publicacion.eliminado_publicacion=0)) OR (publicacion.tipo_deseado_2_publicacion IN (SELECT tipo.nombre_tipo FROM publicacion JOIN tipo ON publicacion.id_tipo_publicacion=tipo.id_tipo WHERE publicacion.id_publicacion=?1 AND publicacion.eliminado_publicacion=0))) AND publicacion.eliminado_publicacion=0", nativeQuery = true)
    List<Publicacion> traerTodoPorOfertaIdPublicacion(Long idPublicacion);
    @Query(value = "SELECT * FROM publicacion JOIN tipo ON publicacion.id_tipo_publicacion=tipo.id_tipo WHERE tipo.nombre_tipo LIKE %?1% AND eliminado_publicacion=0", nativeQuery = true)
    List<Publicacion> traerTodoPorPalabraClaveTipoPublicacion(String palabra);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN perfil ON publicacion.id_perfil_publicacion=perfil.id_perfil SET publicacion.eliminado_publicacion = 1 WHERE perfil.id_perfil = ?1", nativeQuery = true)
    void eliminarTodoPorIdPerfil(Long id);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN perfil ON publicacion.id_perfil_publicacion=perfil.id_perfil SET publicacion.eliminado_publicacion = 0 WHERE perfil.id_perfil = ?1", nativeQuery = true)
    void restablecerTodoPorIdPerfil(Long id);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN provincia ON publicacion.id_provincia_publicacion=provincia.id_provincia SET publicacion.eliminado_publicacion = 1 WHERE provincia.id_provincia = ?1", nativeQuery = true)
    void eliminarTodoPorIdProvincia(Long id);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN provincia ON publicacion.id_provincia_publicacion=provincia.id_provincia SET publicacion.eliminado_publicacion = 0 WHERE provincia.id_provincia = ?1", nativeQuery = true)
    void restablecerTodoPorIdProvincia(Long id);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN tipo ON publicacion.id_tipo_publicacion=tipo.id_tipo SET publicacion.eliminado_publicacion = 1 WHERE tipo.id_tipo = ?1", nativeQuery = true)
    void eliminarTodoPorIdTipo(Long id);

    @Modifying
    @Query(value = "UPDATE publicacion JOIN tipo ON publicacion.id_tipo_publicacion=tipo.id_tipo SET publicacion.eliminado_publicacion = 0 WHERE tipo.id_tipo = ?1", nativeQuery = true)
    void restablecerTodoPorIdTipo(Long id);

    // inicio trae todo por un parametro
/*
    @Query(value = "SELECT * FROM propiedad JOIN provincia ON propiedad.id_provincia_propiedad=provincia.id_provincia WHERE provincia.nombre LIKE ?1", nativeQuery = true)
    List<Propiedad> traerTodoPorProvincia(String provincia);

    @Query(value = "SELECT * FROM propiedad JOIN propiedad ON propiedad.id_provincia_propiedad=provincia.id_provincia JOIN localidad ON provincia.id_provincia=localidad.id_provincia_localidad WHERE localidad.nombre_localidad LIKE ?1", nativeQuery = true)
    List<Propiedad> traerTodoPorLocalidad(String departamento);

    @Query(value = "SELECT * FROM propiedad WHERE superficie_propiedad BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Propiedad> traerTodoPorRangoSuperficie(Integer superficieMin, Integer superficieMax);

    @Query(value = "SELECT * FROM propiedad WHERE superficie_propiedad=?1", nativeQuery = true)
    List<Propiedad> traerTodoPorSuperficie(Integer superficie);

    @Query(value = "SELECT * FROM propiedad JOIN tipo_propiedad ON propiedad.id_tipo_propiedad_propiedad=tipo_propiedad.id_tipo_propiedad WHERE tipo_propiedad.nombre LIKE ?1", nativeQuery = true)
    List<Propiedad> traerTodoPorTipo(String tipo);

    @Query(value = "SELECT * FROM propiedad JOIN perfil ON propiedad.id_perfil_propiedad=perfil.id_perfil WHERE perfil.id_perfil=?1", nativeQuery = true)
    List<Propiedad> traerTodoPorIdPerfil(Long idPerfil);



    @Query(value = "SELECT * FROM propiedad JOIN perfil ON propiedad.id_perfil_propiedad=perfil.id_perfil WHERE propiedad.deseado_propiedad=1 AND perfil.id_perfil=?1", nativeQuery = true)
    List<Propiedad> traerTodoDeseadoPorIdPerfil(Long idPerfil);

    @Query(value = "SELECT * FROM propiedad JOIN perfil ON propiedad.id_perfil_propiedad=perfil.id_perfil WHERE propiedad.deseado_propiedad=0 AND perfil.id_perfil=?1", nativeQuery = true)
    List<Propiedad> traerTodoOfrecidoPorIdPerfil(Long idPerfil);
    // fin trae todo por un parametro
*/
    /*






    descripcion_propiedad

    tipo_propiedad

    @Column(name = "deseado_propiedad", nullable = false)
    private Boolean deseado;

    //buscar por superficie descendente
*/
   /* @Modifying
    @Query("UPDATE Propiedad p SET p.eliminado = false WHERE p.id = ?1")
    void enableById(Long id);*/

}
