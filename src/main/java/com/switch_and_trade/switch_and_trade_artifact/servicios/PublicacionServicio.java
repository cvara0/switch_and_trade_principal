package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Publicacion;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PublicacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionServicio {

    private final PublicacionRepositorio publicacionRepositorio;
    private final FotoServicio fotoServicio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Publicacion dto, MultipartFile foto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTipo(dto.getTipo());
        publicacion.setPerfil(dto.getPerfil());
        if (!foto.isEmpty()) publicacion.setFoto(fotoServicio.copy(foto));
        publicacion.setDescripcionCorta(dto.getDescripcionCorta());
        publicacion.setDescripcion(dto.getDescripcion());
        publicacion.setProvincia(dto.getProvincia());
        publicacion.setLocalidad(dto.getLocalidad());
        publicacion.setTipoDeseado1(dto.getTipoDeseado1());
        publicacion.setTipoDeseado2(dto.getTipoDeseado2());
        publicacion.setEliminado(false);
        publicacionRepositorio.save(publicacion);
    }

    @Transactional
    public void actualizar(Publicacion dto, MultipartFile foto) {
        Publicacion publicacion = publicacionRepositorio.findById(dto.getId()).get();

        publicacion.setTipo(dto.getTipo());
        publicacion.setPerfil(dto.getPerfil());
        if (!foto.isEmpty()) publicacion.setFoto(fotoServicio.copy(foto));
        publicacion.setDescripcionCorta(dto.getDescripcionCorta());
        publicacion.setDescripcion(dto.getDescripcion());
        publicacion.setProvincia(dto.getProvincia());
        publicacion.setLocalidad(dto.getLocalidad());
        publicacion.setTipoDeseado1(dto.getTipoDeseado1());
        publicacion.setTipoDeseado2(dto.getTipoDeseado2());
        publicacion.setEliminado(dto.getEliminado());

        publicacionRepositorio.save(publicacion);
    }



    @Transactional(readOnly = true)
    public Publicacion traerPorId(Long id) {
        return publicacionRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Publicacion> traerTodo() {
        return publicacionRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        publicacionRepositorio.deleteById(id);
    }
//fin metodos basicos

// inicio metodos personalizados


    @Transactional(readOnly = true)
    public List<Publicacion> traerTodoNoEliminado() {
        return publicacionRepositorio.traerTodoNoEliminado();
    }

    @Transactional(readOnly = true)
    public List<Publicacion> traerTodoNoEliminadoPorIdPerfil(Long id) {
        return publicacionRepositorio.traerTodoNoEliminadoPorIdPerfil(id);
    }
    // fin metodos personalizados

}
