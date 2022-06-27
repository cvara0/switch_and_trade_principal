package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Tipo;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PublicacionServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.TipoServicio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tipos")
@RequiredArgsConstructor
@Getter
public class TipoControlador {
    private final TipoServicio tipoServicio;
    private final PublicacionServicio publicacionServicio;


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/tabla")
    public ModelAndView tabla(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-administrar-tipo.html");
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("listaTodoTipo", tipoServicio.traerTodo());
        return mav;
    }
/*TODO administrar tipos, tocar la base de datos para dar de baja segun se elimine cada cosa*/
@PreAuthorize("hasRole('ADMINISTRADOR')")
@GetMapping("/formulario-insertar")
public ModelAndView formularioInsertar(HttpSession session) {
    ModelAndView mav = new ModelAndView("formulario-administrar-tipo.html");
    mav.addObject("idPerfil",session.getAttribute("id"));
    mav.addObject("accion", "insertar");
    mav.addObject("objetoTipo", new Tipo());
    return mav;
}

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id, HttpSession session) {
        ModelAndView mav = new ModelAndView("formulario-administrar-tipo.html");//continuar aca
        mav.addObject("idPerfil",session.getAttribute("id"));
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
        tipoServicio.restablecerPorId(id);/*TODO ver el tema de eliminaciones*/
        publicacionServicio.restablecerTodoPorIdTipo(id);
        return new RedirectView("/tipos/tabla");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/tipos/tabla");
        tipoServicio.eliminarPorId(id);
        publicacionServicio.eliminarTodoPorIdTipo(id);
        return redirect;
    }

}

/*
    */
