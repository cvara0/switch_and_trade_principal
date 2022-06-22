package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.ProvinciaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinciaServicio {
    private final ProvinciaRepositorio provinciaRepositorio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Provincia dto) {
        Provincia provincia = new Provincia();
        provincia.setNombre(dto.getNombre());
        provincia.setEliminado(false);
        provinciaRepositorio.save(provincia);
    }

    @Transactional
    public void actualizar(Provincia dto) {
        Provincia provincia= provinciaRepositorio.findById(dto.getId()).get();
        provincia.setNombre(dto.getNombre());
        provincia.setEliminado(dto.getEliminado());
        provinciaRepositorio.save(provincia);
    }

    @Transactional(readOnly = true)
    public Provincia traerPorId(Long id) {
        return provinciaRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Provincia> traerTodo() {
        return provinciaRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        provinciaRepositorio.deleteById(id);
    }
//fin metodos basicos

}
