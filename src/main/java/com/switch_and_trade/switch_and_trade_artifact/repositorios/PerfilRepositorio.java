package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {

    boolean existsByEmail(String email);
    Optional<Perfil> findByEmail(String email);

    List<Perfil> findByEliminado(Boolean eliminado);

/*
    @Query(value = "SELECT * FROM usuario ORDER BY nombre_usuario ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenNombreAsc();

    @Query(value = "SELECT * FROM usuario ORDER BY apellido_usuario ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenApellidoAsc();

    @Query(value = "SELECT * FROM usuario JOIN provincia ON usuario.id_provincia_usuario=provincia.id_provincia ORDER BY provincia.nombre_provincia ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenProvinciaAsc();

    @Query(value = "SELECT * FROM usuario JOIN provincia ON usuario.id_provincia_usuario=provincia.id_provincia JOIN localidad ON provincia.id_provincia=localidad.id_provincia_localidad ORDER BY localidad.nombre_localidad ASC",nativeQuery = true)
    List<Perfil> traerTodoOrdenLocalidadAsc();

    @Query(value = "SELECT * FROM usuario WHERE telefono_usuario=?1",nativeQuery = true)
    Perfil traerPorTelefono(Integer telefono);
    */
}
