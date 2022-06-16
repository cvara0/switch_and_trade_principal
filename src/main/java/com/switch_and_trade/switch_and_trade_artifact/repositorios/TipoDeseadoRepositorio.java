package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoDeseado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDeseadoRepositorio extends JpaRepository<TipoDeseado, Long> {

}
