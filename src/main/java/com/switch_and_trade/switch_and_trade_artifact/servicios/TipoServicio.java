package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Tipo;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.TipoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoServicio {

    private final TipoRepositorio tipoRepositorio;

    //inicio metodos basicos
    @Transactional
    public void crear(Tipo dto) {
        Tipo tipo = new Tipo();
        tipo.setEliminado(false);
        tipo.setNombre(dto.getNombre());
        tipoRepositorio.save(tipo);
    }

    @Transactional
    public void actualizar(Tipo dto) {
        Tipo tipo = tipoRepositorio.findById(dto.getId()).get();

        tipo.setEliminado(false);
        tipo.setNombre(dto.getNombre());
        tipoRepositorio.save(tipo);
    }

    @Transactional(readOnly = true)
    public Tipo traerPorId(Long id) {
        return tipoRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Tipo> traerTodo() {
        return tipoRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        tipoRepositorio.deleteById(id);
    }
//fin metodos basicos
}
