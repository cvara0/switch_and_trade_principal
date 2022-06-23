package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Tipo;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.TipoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/tipos")
@RequiredArgsConstructor
public class TipoControlador {
    private final TipoServicio tipoServicio;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/tabla")
    public ModelAndView tabla() {
        ModelAndView mav = new ModelAndView("tabla-administrar-tipo.html");
        mav.addObject("listaTodotipo", tipoServicio.traerTodo());
        return mav;
    }

@PreAuthorize("hasRole('ADMINISTRADOR')")
@GetMapping("/formulario-insertar")
public ModelAndView formularioInsertar() {
    ModelAndView mav = new ModelAndView("formulario-administrar-tipo.html");
    mav.addObject("accion", "insertar");
    mav.addObject("objetotipo", new Tipo());
    return mav;
}

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario-administrar-tipo.html");//continuar aca
        mav.addObject("accion", "actualizar");
        mav.addObject("objetoTipo", tipoServicio.traerPorId(id));
        return mav;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/insertar")
    public RedirectView insertar(Tipo dto) {
        RedirectView redirect = new RedirectView("/tipos/tabla");
        try {
            tipoServicio.insertar(dto);
        } catch (IllegalArgumentException e) {
            redirect.setUrl("/tipos/formulario-insertar");
        }

        return redirect;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Tipo dto) {
        RedirectView redirect = new RedirectView("/tipos/tabla");
        tipoServicio.actualizar(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/restablecer/{id}")
    public RedirectView restablecer(@PathVariable Long id) {
        tipoServicio.restablecerPorId(id);
        return new RedirectView("/tipos/tabla");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/tipos/tabla");
        tipoServicio.eliminarPorId(id);
        return redirect;
    }

}

/*
    */
