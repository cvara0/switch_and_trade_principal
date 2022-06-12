package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {
    @Query(value = "SELECT * FROM provincia ORDER BY nombre_provincia ASC", nativeQuery = true)
    List<Provincia> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM provincia WHERE eliminado_provincia=1", nativeQuery = true)
    List<Provincia> traerTodoEliminado();

    @Query(value = "SELECT * FROM provincia WHERE eliminado_provincia=0", nativeQuery = true)
    List<Provincia> traerTodoNoEliminado();

    @Modifying
    @Query("UPDATE Provincia p SET p.eliminado = false WHERE p.id = ?1")
    void restablecerPorId(Long id);
}
