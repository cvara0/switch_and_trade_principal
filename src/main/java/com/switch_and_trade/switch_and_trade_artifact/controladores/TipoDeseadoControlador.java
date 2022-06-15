package com.switch_and_trade.switch_and_trade_artifact.controladores;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tipos-deseados")
@RequiredArgsConstructor
public class TipoDeseadoControlador {

    @GetMapping("/formulario-insertar-tipo-deseado")
    public ModelAndView formularioInsertarTipoDeseado() {
        ModelAndView mav = new ModelAndView("formulario-insertar-tipo-deseado.html");
        return mav;
    }

}
