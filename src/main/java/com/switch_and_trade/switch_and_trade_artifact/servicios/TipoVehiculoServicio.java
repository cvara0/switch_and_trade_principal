package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoDeseado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoVehiculoServicio {
/*
    private final TipoVehiculoRepositorio tipoVehiculoRepositorio;

    //inicio metodos basicos
    @Transactional
    public void crear(TipoDeseado dto) {
        TipoDeseado tipoDeseado = new TipoDeseado();
        tipoDeseado.setTipoPropiedad(dto.getTipoPropiedad());//tipo propiedad ya esta precargado recordar
        tipoDeseado.setTipoVehiculo(dto.getTipoVehiculo());
        tipoDeseado.setEliminado(false);
        tipoDeseadoRepositorio.save(tipoDeseado);
    }

    @Transactional
    public void actualizar(TipoDeseado dto) {
        TipoDeseado tipoDeseado = tipoDeseadoRepositorio.findById(dto.getId()).get();

        tipoDeseado.setTipoPropiedad(dto.getTipoPropiedad());//tipo propiedad ya esta precargado recordar
        tipoDeseado.setTipoVehiculo(dto.getTipoVehiculo());
        tipoDeseado.setEliminado(false);
        tipoDeseadoRepositorio.save(tipoDeseado);
    }

    @Transactional(readOnly = true)
    public TipoDeseado traerPorId(Long id) {
        return tipoDeseadoRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<TipoDeseado> traerTodo() {
        return tipoDeseadoRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        tipoDeseadoRepositorio.deleteById(id);
    }

 */
//fin metodos basicos
}
