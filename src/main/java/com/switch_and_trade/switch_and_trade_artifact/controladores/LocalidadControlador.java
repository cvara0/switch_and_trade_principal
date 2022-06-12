package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Localidad;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.servicios.LocalidadServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Controller
@RequestMapping("/localidades")
@RequiredArgsConstructor
public class LocalidadControlador {

    private final LocalidadServicio localidadServicio;
    private final ProvinciaServicio provinciaServicio;

    @GetMapping("/tabla")//muestra todo con botones
    public ModelAndView tabla() {
        ModelAndView mav = new ModelAndView("tabla-localidad");

        mav.addObject("todoLocalidad", localidadServicio.traerTodo());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-insertar")
    public ModelAndView formularioInsertar() {
        ModelAndView mav = new ModelAndView("formulario-insertar-localidad");
        mav.addObject("objetoLocalidadVacio", new Localidad());
        mav.addObject("todoProvincia",provinciaServicio.traerTodo() );
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario-actualizar-localidad");
        mav.addObject("objetoLocalidadLleno", localidadServicio.traerPorId(id));
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/insertar")//accion insertar
    public RedirectView insertar(Localidad dto) {
        RedirectView redirect = new RedirectView("/localidades/tabla");
        try {
            localidadServicio.insertar(dto);
        } catch (IllegalArgumentException e) {
            redirect.setUrl("/localidades/formulario-insertar");
        }
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Provincia dto) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.actualizar(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/restablecer/{id}")
    public RedirectView enable(@PathVariable Long id) {
        provinciaServicio.restablecerPorId(id);
        return new RedirectView("/authors");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.eliminarPorId(id);
        return redirect;
    }

}
