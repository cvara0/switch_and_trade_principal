package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.servicios.PropiedadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/publicaciones")
@RequiredArgsConstructor
public class PublicacionControlador {

    private final PropiedadServicio propiedadServicio;
    @GetMapping("/tabla-todo-publicacion-perfil")
    public ModelAndView tablaTodoPublicacionPerfil(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-perfil.html");
        //tendria que agregar vehiculo a otra lista y mandarle tambien
        mav.addObject("listaPropiedadPerfil",propiedadServicio.traerTodoNoEliminadoPorIdPerfil((Long) session.getAttribute("id")));
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion")
    public ModelAndView tablaTodoPropiedad() {//llaman al mismo html con vehiculo pero le mandan cosas diferentes
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion.html");
        mav.addObject("listaPropiedad",propiedadServicio.traerTodoNoEliminado());
        return mav;
    }

}
