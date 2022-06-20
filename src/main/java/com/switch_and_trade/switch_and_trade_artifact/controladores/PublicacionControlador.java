package com.switch_and_trade.switch_and_trade_artifact.controladores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/publicaciones")
@RequiredArgsConstructor
public class PublicacionControlador {
    /*
    * en este controlador se vana tomar datos de propiedades y vehiculos para mostarlos en conjunto*/
    @GetMapping("/tabla-todo-publicacion-perfil")///{id}se busca en las publicaciones que tengan ese id de perfil
    public ModelAndView tablaTodoPublicacionPerfil(/*@PathVariable Long idPerfil*/) {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-perfil.html");
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion")
    public ModelAndView tablaTodoPropiedad() {//llaman al mismo html con vehiculo pero le mandan cosas diferentes
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion.html");

        return mav;
    }

}
