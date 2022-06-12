package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.servicios.VehiculoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoControlador {

    private final VehiculoServicio vehiculoServicio;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoVehiculo(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");

        mav.addObject("vehiculo",vehiculoServicio.traerTodo());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/anio-asc")
    public ModelAndView traerTodoPorAnioAsc(){
        ModelAndView mav=new ModelAndView("");
        mav.addObject("TodoVehiculoPorAnioAsc",vehiculoServicio.traerTodoOrdenAnio(true));
        return mav;
    }
/*
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorAnioDesc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorAnioDesc());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorMarcaAsc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorMarcaAsc());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorMarcaDesc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorMarcaDesc());
        return mav;
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorModeloAsc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorModeloAsc());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorModeloDesc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorModeloDesc());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorTipoAsc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorTipoAsc());
        return mav;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ModelAndView traerTodoPorTipoDesc(){
        ModelAndView mav=new ModelAndView("tabla-vehiculo");
        mav.addObject("vehiculo",vehiculoServicio.traerTodoPorTipoDesc());
        return mav;
    }
*/

}

