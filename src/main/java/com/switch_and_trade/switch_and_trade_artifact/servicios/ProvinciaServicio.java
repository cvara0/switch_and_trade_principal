package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Localidad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.LocalidadRepositorio;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.ProvinciaRepositorio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProvinciaServicio {
    private final ProvinciaRepositorio provinciaRepositorio;

    //inicio metodos basicos
    @Transactional
    public void insertar(Provincia dto){
        Provincia provincia=new Provincia();
        provincia.setNombre(dto.getNombre());
        provincia.setEliminado(dto.getEliminado());

        provinciaRepositorio.save(provincia);
    }
    @Transactional
    public void actualizar (Provincia dto){
        Provincia provincia=provinciaRepositorio.findById(dto.getId()).get();
        provincia.setNombre(dto.getNombre());
        provincia.setEliminado(dto.getEliminado());
        provinciaRepositorio.save(provincia);
    }
    @Transactional
    public List<Provincia> traerTodo(){
        return provinciaRepositorio.findAll();
    }

    @Transactional
    public Provincia traerPorId(Long id){
        return provinciaRepositorio.findById(id).get();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        provinciaRepositorio.deleteById(id);
    }
//fin metodos basicos

    // inicio metodos personalizados
    @Transactional
    public List<Provincia> traerTodoOrdenNombreAsc(){
        return provinciaRepositorio.traerTodoOrdenNombreAsc();
    }
    @Transactional
    List<Provincia> traerTodoEliminado(){
        return provinciaRepositorio.traerTodoEliminado();
    }
    @Transactional
    List<Provincia> traerTodoNoEliminado(){
        return provinciaRepositorio.traerTodoNoEliminado();
    }

    @Transactional
    public void restablecerPorId(Long id) {
       provinciaRepositorio.restablecerPorId(id);
    }
//seguir con provincia y localidad
// fin metodos personalizados

}

