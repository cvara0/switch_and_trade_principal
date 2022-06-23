package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepositorio extends JpaRepository<Tipo, Long> {
    @Modifying
    @Query(value = "UPDATE tipo SET eliminado_tipo = 0 WHERE id_tipo = ?1", nativeQuery = true)
    void restablecerPorId(Long id);
}
