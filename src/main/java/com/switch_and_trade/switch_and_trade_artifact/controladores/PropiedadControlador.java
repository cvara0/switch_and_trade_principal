package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Propiedad;
import com.switch_and_trade.switch_and_trade_artifact.listasPreCargadas.ListaProvincia;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PropiedadServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.TipoPropiedadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/propiedades")
@RequiredArgsConstructor
public class PropiedadControlador {

    private final PropiedadServicio propiedadServicio;
    private final ListaProvincia listaProvincia;
    private final PerfilServicio perfilServicio;
    private final TipoPropiedadServicio tipoPropiedadServicio;

    @GetMapping("/tabla-todo-propiedad")
    public ModelAndView tablaTodoPublicacionPropiedad() {
        ModelAndView mav = new ModelAndView("tabla-todo-propiedad.html");
        return mav;
    }

    @GetMapping("/formulario-insertar-propiedad")
    public ModelAndView formularioInsertarPropiedad(HttpSession session) {//@PathVariable Long id,
        ModelAndView mav = new ModelAndView("formulario-insertar-o-actualizar-propiedad.html");

        //if(!session.getAttribute("id").equals(id))
           // return new ModelAndView("redirect:/");

        Perfil perfil=perfilServicio.traerPorId((Long) session.getAttribute("id"));
        Propiedad propiedad =new Propiedad();
        propiedad.setPerfil(perfil);

        mav.addObject("objetoPropiedad", propiedad);
        mav.addObject("listaTipoPropiedad",tipoPropiedadServicio.traerTodo());
        mav.addObject("listaProvincia", listaProvincia.getListaProvincia());
        mav.addObject("listaTipoDeseado",listaProvincia.getListaProvincia() /*concatenar arraylist tipo deseado tipo propiedad*/);
        return mav;
    }

    @GetMapping("/formulario-actualizar-propiedad/{id}")
    public ModelAndView formularioActualizarPropiedad(HttpSession session,@PathVariable Long id) {//,
        ModelAndView mav = new ModelAndView("formulario-insertar-o-actualizar-propiedad.html");

        //if(!session.getAttribute("id").equals(id))
        // return new ModelAndView("redirect:/");

        Propiedad propiedad =propiedadServicio.traerPorId(id);

        mav.addObject("objetoPropiedad", propiedad);
        mav.addObject("listaTipoPropiedad",tipoPropiedadServicio.traerTodo());
        mav.addObject("listaProvincia", listaProvincia.getListaProvincia());
        mav.addObject("listaTipoDeseado",listaProvincia.getListaProvincia() /*concatenar arraylist tipo deseado tipo propiedad*/);
        return mav;
    }


    @PostMapping("/insertar-propiedad")
    public RedirectView insertarPropiedad(Propiedad dto, @RequestParam(required = false) MultipartFile propiedadfotoname) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion-perfil");
        try {
            propiedadServicio.insertar(dto,propiedadfotoname);
        } catch (IllegalArgumentException e) {
            redirect.setUrl("/provincias/formulario-insertar-propiedad");
        }
        return redirect;
    }

    @PostMapping("/actualizar-propiedad")
    public RedirectView actualizar(Propiedad dto, @RequestParam(required = false) MultipartFile propiedadfotoname) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion-perfil");
        propiedadServicio.actualizar(dto,propiedadfotoname);
        return redirect;
    }



/*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-insertar")
    public ModelAndView formularioInsertar() {
        ModelAndView mav = new ModelAndView("formulario-insertar-provincia");
        mav.addObject("objetoProvinciaVacio", new Provincia());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario-actualizar-provincia");
        mav.addObject("objetoProvinciaLleno", provinciaServicio.traerPorId(id));
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Provincia dto) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.actualizar(dto);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/restablecer/{id}")
    public RedirectView restablecer(@PathVariable Long id) {
        provinciaServicio.restablecerPorId(id);
        return new RedirectView("/provincias/tabla");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/provincias/tabla");
        provinciaServicio.eliminarPorId(id);
        return redirect;
    }
    */


}
