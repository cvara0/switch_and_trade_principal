package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PropiedadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/propiedades")
@RequiredArgsConstructor
public class PropiedadControlador {

        private final PropiedadServicio propiedadServicio;

        @GetMapping("/formulario-insertar-propiedad")
        public ModelAndView formularioInsertarPropiedad() {
            ModelAndView mav = new ModelAndView("formulario-insertar-propiedad.html");
            //mav.addObject("objetoPropiedadVacio",new Propiedad());
            return mav;
        }

    @PostMapping("/insertar-propiedad")
    public RedirectView insertarPropiedad(Propiedad dto) {
            RedirectView redirect=new RedirectView("/tipos-deseados/formulario-insertar-tipo-deseado");
            propiedadServicio.crear(dto);

        return redirect;
    }


}
