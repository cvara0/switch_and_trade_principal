package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PropiedadRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PropiedadServicio {

    private final PropiedadRepositorio propiedadRepositorio;

    //inicio metodos basicos
    @Transactional
    public void crear(Propiedad dto) {
        Propiedad propiedad = new Propiedad();
        propiedad.setSuperficie(dto.getSuperficie());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setFoto(dto.getFoto());
        propiedad.setDeseado(dto.getDeseado());
        propiedad.setEliminado(dto.getEliminado());
        propiedad.setTipoPropiedad(dto.getTipoPropiedad());
        propiedad.setProvincia(dto.getProvincia());

        propiedadRepositorio.save(propiedad);
    }

    @Transactional
    public void actualizar(Propiedad dto) {
        Propiedad propiedad = propiedadRepositorio.findById(dto.getId()).get();

        propiedad.setSuperficie(dto.getSuperficie());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setFoto(dto.getFoto());
        propiedad.setDeseado(dto.getDeseado());
        propiedad.setEliminado(dto.getEliminado());
        propiedad.setTipoPropiedad(dto.getTipoPropiedad());
        propiedad.setProvincia(dto.getProvincia());

        propiedadRepositorio.save(propiedad);
    }

    @Transactional(readOnly = true)
    public Propiedad traerPorId(Long id) {
        return propiedadRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodo() {
        return propiedadRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        propiedadRepositorio.deleteById(id);
    }
//fin metodos basicos

// inicio metodos personalizados
    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenProvinciaAsc() {
        return propiedadRepositorio.traerTodoOrdenProvinciaAsc();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenLocalidadAsc() {
        return propiedadRepositorio.traerTodoOrdenLocalidadAsc();
    }
    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenSuperficie(Boolean ordenAsc) {
        return ordenAsc ? propiedadRepositorio.traerTodoOrdenSuperficieAsc() : propiedadRepositorio.traerTodoOrdenSuperficieDesc();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenTipoAsc() {
        return propiedadRepositorio.traerTodoOrdenTipoAsc();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenEliminado(Boolean eliminado) {
        return eliminado ? propiedadRepositorio.traerTodoEliminado() : propiedadRepositorio.traerTodoNoEliminado();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOrdenDeseado(Boolean deseado) {
        return deseado ? propiedadRepositorio.traerTodoDeseado() : propiedadRepositorio.traerTodoOfrecido();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorProvincia(String provincia) {
        return propiedadRepositorio.traerTodoPorProvincia(provincia);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorLocalidad(String localidad) {
        return propiedadRepositorio.traerTodoPorLocalidad(localidad);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorRangoSuperficie(Integer superficieMin, Integer superficieMax) {
        return propiedadRepositorio.traerTodoPorRangoSuperficie(superficieMin, superficieMax);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorSuperficie(Integer superficie) {
        return propiedadRepositorio.traerTodoPorSuperficie(superficie);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorTipo(String tipo) {
        return propiedadRepositorio.traerTodoPorTipo(tipo);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorIdPerfil(Long idPerfil) {
        return propiedadRepositorio.traerTodoPorIdPerfil(idPerfil);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoPorDesdeadoPorIdPerfil(Long idPerfil) {
        return propiedadRepositorio.traerTodoDeseadoPorIdPerfil(idPerfil);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> traerTodoOfrecidoPorIdPerfil(Long idPerfil) {
        return propiedadRepositorio.traerTodoOfrecidoPorIdPerfil(idPerfil);
    }
    // fin metodos personalizados

}
