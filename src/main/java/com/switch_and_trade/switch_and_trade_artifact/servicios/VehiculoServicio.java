package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Vehiculo;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.VehiculoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoServicio {
    private final VehiculoRepositorio vehiculoRepositorio;
//inicio metodos basicos

    @Transactional
    public void insertar(Vehiculo dto) {

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setDescripcion(dto.getDescripcion());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());
        vehiculo.setFoto(dto.getFoto());
        vehiculo.setDeseado(dto.getDeseado());
        vehiculo.setEliminado(dto.getEliminado());
        vehiculoRepositorio.save(vehiculo);
    }

    @Transactional
    public void actualizar(Vehiculo dto) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(dto.getId()).get();
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setDescripcion(dto.getDescripcion());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());
        vehiculo.setFoto(dto.getFoto());
        vehiculo.setDeseado(dto.getDeseado());
        vehiculo.setEliminado(dto.getEliminado());
        vehiculoRepositorio.save(vehiculo);
    }

    @Transactional(readOnly = true)
    public Vehiculo traerPorId(Long id) {
        return vehiculoRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodo() {
        return vehiculoRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        vehiculoRepositorio.deleteById(id);
    }
//fin metodos basicos

    // inicio metodos personalizados

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenAnio(Boolean ordenAsc) {
        return ordenAsc ? vehiculoRepositorio.traerTodoOrdenAnioAsc() : vehiculoRepositorio.traerTodoOrdenAnioDesc();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenMarca(Boolean ordenAsc) {
        return ordenAsc ? vehiculoRepositorio.traerTodoOrdenMarcaAsc() : vehiculoRepositorio.traerTodoOrdenMarcaDesc();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenModelo(Boolean ordenAsc) {
        return ordenAsc ? vehiculoRepositorio.traerTodoOrdenModeloAsc() : vehiculoRepositorio.traerTodoOrdenModeloDesc();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenTipo(Boolean ordenAsc) {
        return ordenAsc ? vehiculoRepositorio.traerTodoOrdenTipoAsc() : vehiculoRepositorio.traerTodoOrdenTipoDesc();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenDeseado(Boolean deseado) {
        return deseado ? vehiculoRepositorio.traerTodoDeseado() : vehiculoRepositorio.traerTodoOfrecido();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoOrdenEliminado(Boolean eliminado) {
        return eliminado ? vehiculoRepositorio.traerTodoEliminado() : vehiculoRepositorio.traerTodoNoEliminado();
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoPorAnio(Integer anio) {
        return vehiculoRepositorio.traerTodoPorAnio(anio);
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoPorParametroMarca(String marca) {
        return vehiculoRepositorio.traerTodoPorMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoPorModelo(String modelo) {
        return vehiculoRepositorio.traerTodoPorModelo(modelo);
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> traerTodoPorTipo(String tipo) {
        return vehiculoRepositorio.traerTodoPorTipo(tipo);
    }
    // fin metodos personalizados

}
