package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.TipoDeseado;
import com.switch_and_trade.switch_and_trade_artifact.servicios.TipoDeseadoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/tipos-deseados")
@RequiredArgsConstructor
public class TipoDeseadoControlador {

    private final TipoDeseadoServicio tipoDeseadoServicio;
    @GetMapping("/formulario-insertar-tipo-deseado")
    public ModelAndView formularioInsertarTipoDeseado() {
        ModelAndView mav = new ModelAndView("formulario-insertar-tipo-deseado.html");
        return mav;
    }
/* propiedad si se persiste pero tipo deseado se seleccionan varios ya cargados
* y se aniaden, y si se desvincula tipo */


}
