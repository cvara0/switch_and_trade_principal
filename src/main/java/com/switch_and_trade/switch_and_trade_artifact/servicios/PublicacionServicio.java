package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Publicacion;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Vehiculo;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PublicacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionServicio {
    private final PublicacionRepositorio publicacionRepositorio;
    private final VehiculoServicio vehiculoServicio;
    private final PropiedadServicio propiedadServicio;
    private final PerfilServicio perfilServicio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Publicacion dto) {
        Publicacion publicacion = new Publicacion();
        //publicacion.setPerfil(dto.getPerfil());
        publicacion.setPropiedad(dto.getPropiedad());
        publicacion.setVehiculo(dto.getVehiculo());
        publicacion.setEliminado(dto.getEliminado());

        publicacionRepositorio.save(publicacion);
    }

    @Transactional
    public void actualizar(Publicacion dto) {
        Publicacion publicacion = publicacionRepositorio.findById(dto.getId()).get();

        //publicacion.setPerfil(dto.getPerfil());
        publicacion.setPropiedad(dto.getPropiedad());
        publicacion.setVehiculo(dto.getVehiculo());
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

// fin metodos personalizados




}



