package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Localidad;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.LocalidadRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalidadServicio {

    private final LocalidadRepositorio localidadRepositorio;

    //inicio metodos basicos
    public void insertar(Localidad dto){
        Localidad localidad=new Localidad();
        localidad.setNombre(dto.getNombre());
        localidad.setProvincia(dto.getProvincia());
        localidad.setEliminado(dto.getEliminado());
        localidadRepositorio.save(localidad);
    }
    public void actualizar (Localidad dto){
        Localidad localidad=localidadRepositorio.findById(dto.getId()).get();
        localidad.setNombre(dto.getNombre());
        localidad.setProvincia(dto.getProvincia());
        localidad.setEliminado(dto.getEliminado());
        localidadRepositorio.save(localidad);
    }
    public List<Localidad> traerTodo(){
        return localidadRepositorio.findAll();
    }

    public Localidad traerPorId(Long id){
        return localidadRepositorio.findById(id).get();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        localidadRepositorio.deleteById(id);
    }
//fin metodos basicos

// inicio metodos personalizados
    public List<Localidad> traerTodoOrdenNombreAsc(){
        return localidadRepositorio.traerTodoOrdenNombreAsc();
    }
    List<Localidad> traerTodoEliminado(){
        return localidadRepositorio.traerTodoEliminado();
    }

    List<Localidad> traerTodoNoEliminado(){
        return localidadRepositorio.traerTodoNoEliminado();
    }

    List<Localidad> traerTodoPorNombreProvincia(String nombreProvincia){
        return localidadRepositorio.traerTodoPorNombreProvincia(nombreProvincia);
    }

// fin metodos personalizados

}
