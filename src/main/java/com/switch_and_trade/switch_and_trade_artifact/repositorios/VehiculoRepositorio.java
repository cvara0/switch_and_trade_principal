package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long> {

    // inicio trae todo y ordena
    @Query(value = "SELECT * FROM vehiculo ORDER BY anio_vehiculo ASC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenAnioAsc();

    @Query(value = "SELECT * FROM vehiculo ORDER BY anio_vehiculo DESC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenAnioDesc();

    //
    @Query(value = "SELECT * FROM vehiculo ORDER BY marca_vehiculo ASC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenMarcaAsc();

    @Query(value = "SELECT * FROM vehiculo ORDER BY marca_vehiculo DESC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenMarcaDesc();

    //
    @Query(value = "SELECT * FROM vehiculo ORDER BY modelo_vehiculo ASC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenModeloAsc();

    @Query(value = "SELECT * FROM vehiculo ORDER BY modelo_vehiculo DESC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenModeloDesc();

    //
    @Query(value = "SELECT * FROM vehiculo JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo_vehiculo=tipo_vehiculo.id_tipo_vehiculo ORDER BY tipo_vehiculo.nombre ASC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenTipoAsc();

    @Query(value = "SELECT * FROM vehiculo JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo_vehiculo=tipo_vehiculo.id_tipo_vehiculo ORDER BY tipo_vehiculo.nombre DESC", nativeQuery = true)
    List<Vehiculo> traerTodoOrdenTipoDesc();

    //
    @Query(value = "SELECT * FROM vehiculo WHERE deseado_vehiculo=1", nativeQuery = true)
    List<Vehiculo> traerTodoDeseado();

    @Query(value = "SELECT * FROM vehiculo WHERE deseado_vehiculo=0", nativeQuery = true)
    List<Vehiculo> traerTodoOfrecido();

    @Query(value = "SELECT * FROM vehiculo WHERE eliminado_vehiculo=1", nativeQuery = true)
    List<Vehiculo> traerTodoEliminado();

    @Query(value = "SELECT * FROM vehiculo WHERE eliminado_vehiculo=0", nativeQuery = true)
    List<Vehiculo> traerTodoNoEliminado();

    // fin trae todo y ordena

    // inicio trae todo por un parametro
    @Query(value = "SELECT * FROM vehiculo WHERE anio_vehiculo=?1", nativeQuery = true)
    List<Vehiculo> traerTodoPorAnio(Integer anio);

    //
    @Query(value = "SELECT * FROM vehiculo WHERE marca_vehiculo LIKE ?1", nativeQuery = true)
    List<Vehiculo> traerTodoPorMarca(String marca);

    //
    @Query(value = "SELECT * FROM vehiculo WHERE modelo_vehiculo LIKE ?1", nativeQuery = true)
    List<Vehiculo> traerTodoPorModelo(String modelo);

    //
    @Query(value = "SELECT * FROM vehiculo JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo_vehiculo=tipo_vehiculo.id_tipo_vehiculo WHERE tipo_vehiculo.nombre LIKE ?1", nativeQuery = true)
    List<Vehiculo> traerTodoPorTipo(String tipo);

    @Query(value = "SELECT * FROM vehiculo JOIN perfil ON vehiculo.id_perfil_vehiculo=perfil.id_perfil WHERE perfil.id_perfil=?1", nativeQuery = true)
    List<Vehiculo> traerTodoPorIdPerfil(Long idPerfil);

    @Query(value = "SELECT * FROM vehiculo JOIN perfil ON vehiculo.id_perfil_vehiculo=perfil.id_perfil WHERE vehiculo.deseado_vehiculo=1 AND perfil.id_perfil=?1", nativeQuery = true)
    List<Vehiculo> traerTodoDeseadoPorIdPerfil(Long idPerfil);

    @Query(value = "SELECT * FROM vehiculo JOIN perfil ON vehiculo.id_perfil_vehiculo=perfil.id_perfil WHERE vehiculo.deseado_vehiculo=0 AND perfil.id_perfil=?1", nativeQuery = true)
    List<Vehiculo> traerTodoOfrecidoPorIdPerfil(Long idPerfil);

    // fin trae todo por un parametro


   /* @Modifying
    @Query("UPDATE Vehiculo v SET v.eliminado = false WHERE p.id = ?1")
    void enableById(Long id);*/


}
