package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PublicacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciaControlador {
    private final ProvinciaServicio provinciaServicio;
    private final PublicacionServicio publicacionServicio;


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/tabla")
    public ModelAndView tabla(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-administrar-provincia.html");
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("listaTodoProvincia", provinciaServicio.traerTodo());
        return mav;
    }

@PreAuthorize("hasRole('ADMINISTRADOR')")
@GetMapping("/formulario-insertar")
public ModelAndView formularioInsertar(HttpSession session) {
    ModelAndView mav = new ModelAndView("formulario-administrar-provincia.html");
    mav.addObject("idPerfil",session.getAttribute("id"));
    mav.addObject("accion", "insertar");
    mav.addObject("objetoProvincia", new Provincia());
    return mav;
}

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id,HttpSession session) {
        ModelAndView mav = new ModelAndView("formulario-administrar-provincia.html");//continuar aca
        mav.addObject("idPerfil",session.getAttribute("id"));/*TODO agragar esto a todos lo mav*/
        mav.addObject("accion", "actualizar");
        mav.addObject("objetoProvincia", provinciaServicio.traerPorId(id));
        return mav;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/insertar")
    public RedirectView insertar(Provincia dto) {
        RedirectView redirect = new RedirectView("/provincias/tabla");

        try {
            provinciaServicio.insertar(dto);
        } catch (IllegalArgumentException e) {
            redirect.setUrl("/provincias/formulario-insertar");
        }

        return redirect;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Provincia dto) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.actualizar(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/restablecer/{id}")
    public RedirectView restablecer(@PathVariable Long id) {
        provinciaServicio.restablecerPorId(id);
        publicacionServicio.restablecerTodoPorIdProvincia(id);
        return new RedirectView("/provincias/tabla");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.eliminarPorId(id);
        publicacionServicio.eliminarTodoPorIdProvincia(id);
        return redirect;
    }

}

/*
    */
