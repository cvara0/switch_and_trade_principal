package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PropiedadServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/propiedades")
@RequiredArgsConstructor
public class PropiedadControlador {

        private final PropiedadServicio propiedadServicio;
        private final ProvinciaServicio provinciaServicio;
        private final PerfilServicio perfilServicio;



    @PostMapping("/insertar-propiedad")
    public RedirectView insertarPropiedad(Propiedad dto) {
            RedirectView redirect=new RedirectView("");
            propiedadServicio.crear(dto);

        return redirect;
    }


}
