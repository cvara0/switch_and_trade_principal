package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoPropiedad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculo, Long> {

}
