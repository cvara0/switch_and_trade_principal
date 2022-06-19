package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoDeseado;
import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoPropiedad;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.TipoDeseadoRepositorio;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.TipoPropiedadRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoPropiedadServicio {

    private final TipoPropiedadRepositorio tipoPropiedadRepositorio;

    //inicio metodos basicos
    @Transactional
    public void crear(TipoPropiedad dto) {
        TipoPropiedad tipoPropiedad = new TipoPropiedad();
        tipoPropiedad.setEliminado(false);
        tipoPropiedad.setNombre(dto.getNombre());
        tipoPropiedadRepositorio.save(tipoPropiedad);
    }

    @Transactional
    public void actualizar(TipoPropiedad dto) {
        TipoPropiedad tipoPropiedad = tipoPropiedadRepositorio.findById(dto.getId()).get();

        tipoPropiedad.setEliminado(false);
        tipoPropiedad.setNombre(dto.getNombre());
        tipoPropiedadRepositorio.save(tipoPropiedad);
    }

    @Transactional(readOnly = true)
    public TipoPropiedad traerPorId(Long id) {
        return tipoPropiedadRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<TipoPropiedad> traerTodo() {
        return tipoPropiedadRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        tipoPropiedadRepositorio.deleteById(id);
    }
//fin metodos basicos
}
