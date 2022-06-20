package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoPropiedad;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PropiedadRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Service
@RequiredArgsConstructor
public class PropiedadServicio {

    private final PropiedadRepositorio propiedadRepositorio;
    private final FotoServicio fotoServicio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Propiedad dto, MultipartFile foto) {
        Propiedad propiedad = new Propiedad();
        propiedad.setTipoPropiedad(dto.getTipoPropiedad());
        propiedad.setPerfil(dto.getPerfil());
        if (!foto.isEmpty()) propiedad.setFoto(fotoServicio.copy(foto));
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setSuperficie(dto.getSuperficie());
        propiedad.setProvincia(dto.getProvincia());
        propiedad.setLocalidad(dto.getLocalidad());
        propiedad.setTipoDeseado1(dto.getTipoDeseado1());
        propiedad.setTipoDeseado2(dto.getTipoDeseado2());
        propiedad.setTipoDeseado3(dto.getTipoDeseado3());
        propiedad.setEliminado(dto.getEliminado());
        propiedadRepositorio.save(propiedad);
    }

    @Transactional
    public void actualizar(Propiedad dto, MultipartFile foto) {
        Propiedad propiedad = propiedadRepositorio.findById(dto.getId()).get();

        propiedad.setTipoPropiedad(dto.getTipoPropiedad());
        propiedad.setPerfil(dto.getPerfil());
        if (!foto.isEmpty()) propiedad.setFoto(fotoServicio.copy(foto));
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setSuperficie(dto.getSuperficie());
        propiedad.setProvincia(dto.getProvincia());
        propiedad.setLocalidad(dto.getLocalidad());
        propiedad.setTipoDeseado1(dto.getTipoDeseado1());
        propiedad.setTipoDeseado2(dto.getTipoDeseado2());
        propiedad.setTipoDeseado3(dto.getTipoDeseado3());
        propiedad.setEliminado(dto.getEliminado());

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
