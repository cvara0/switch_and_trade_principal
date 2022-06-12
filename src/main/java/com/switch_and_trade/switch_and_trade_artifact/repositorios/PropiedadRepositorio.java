package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropiedadRepositorio extends JpaRepository<Propiedad, Long> {

    // inicio trae todo y ordena
    @Query(value = "SELECT * FROM propiedad ORDER BY provincia_propiedad ASC", nativeQuery = true)
    List<Propiedad> traerTodoOrdenProvinciaAsc();

    @Query(value = "SELECT * FROM propiedad JOIN propiedad ON propiedad.id_provincia_propiedad=provincia.id_provincia JOIN localidad ON provincia.id_provincia=localidad.id_provincia_localidad ORDER BY localidad.nombre_localidad ASC", nativeQuery = true)
    List<Propiedad> traerTodoOrdenLocalidadAsc();

    @Query(value = "SELECT * FROM propiedad ORDER BY superficie_propiedad ASC", nativeQuery = true)
    List<Propiedad> traerTodoOrdenSuperficieAsc();

    @Query(value = "SELECT * FROM propiedad ORDER BY superficie_propiedad DESC", nativeQuery = true)
    List<Propiedad> traerTodoOrdenSuperficieDesc();

    @Query(value = "SELECT * FROM propiedad JOIN tipo_propiedad ON propiedad.id_tipo_propiedad_propiedad=tipo_propiedad.id_tipo_propiedad ORDER BY tipo_propiedad.nombre ASC", nativeQuery = true)
    List<Propiedad> traerTodoOrdenTipoAsc();


    @Query(value = "SELECT * FROM propiedad WHERE deseado_propiedad=1", nativeQuery = true)
    List<Propiedad> traerTodoDeseado();

    @Query(value = "SELECT * FROM propiedad WHERE deseado_propiedad=0", nativeQuery = true)
    List<Propiedad> traerTodoOfrecido();

    @Query(value = "SELECT * FROM propiedad WHERE eliminado_propiedad=1", nativeQuery = true)
    List<Propiedad> traerTodoEliminado();

    @Query(value = "SELECT * FROM propiedad WHERE eliminado_propiedad=0", nativeQuery = true)
    List<Propiedad> traerTodoNoEliminado();

    // fin trae todo y ordena

    // inicio trae todo por un parametro

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
