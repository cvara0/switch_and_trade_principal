package com.switch_and_trade.switch_and_trade_artifact.repositorios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {

}
