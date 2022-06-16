package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
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
@RequestMapping("/publicaciones")
@RequiredArgsConstructor
public class PublicacionControlador {
//ver nomenclaturas
    //publicacion-usuario
    //coincidencia-deseado-usuario
    //coincidencia-ofrecido-usaurio
    //tabla-todo-publicacion-vehiculo
    //tabla-todo-publicacion
    //tabla-todo-publicacion-propiedad

    @GetMapping("/tabla-todo-publicacion")
    public ModelAndView tablaTodoPublicacion() {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion.html");
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion-vehiculo")
    public ModelAndView tablaTodoPublicacionVehiculo() {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-vehiculo.html");
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion-propiedad")
    public ModelAndView tablaTodoPublicacionPropiedad() {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-propiedad.html");
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion-perfil")///{id}se busca en las publicaciones que tengan ese id de perfil
    public ModelAndView tablaTodoPublicacionPerfil(/*@PathVariable Long idPerfil*/) {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-formulario-actualizar-perfil.html");
        return mav;
    }

    @GetMapping("/formulario-insertar-publicacion")///{id}se busca en las publicaciones que tengan ese id de perfil
    public ModelAndView publicacionesFormularioInsertarPublicacion(/*@PathVariable Long idPerfil*/) {
        ModelAndView mav = new ModelAndView("formulario-insertar-publicacion.html");
        return mav;
    }



    @GetMapping("/tabla-publicaciones-que-ofrecen-deseados-de-perfil/{id}")
    public ModelAndView tablaPublicacionesQueOfrecenDeseadosDePerfil(@PathVariable Long idPerfil) {
        ModelAndView mav = new ModelAndView("tabla-coincidencias-de-formulario-actualizar-perfil.html");
        return mav;
    }

    @GetMapping("/tabla-publicaciones-que-desean-ofrecidos-de-perfil/{id}")
    public ModelAndView tablaPublicacionesQueDeseanOfrecidosDePerfil(@PathVariable Long idPerfil) {
        ModelAndView mav = new ModelAndView("tabla-coincidencias-de-formulario-actualizar-perfil.html");
        return mav;
    }

    @GetMapping("/tabla-publicaciones-con-coincidencia-exacta/{id}")
    public ModelAndView tablaPublicacionesQueQfrecenQeseadosDePerfil(@PathVariable Long idPerfil) {
        ModelAndView mav = new ModelAndView("tabla-coincidencias-de-formulario-actualizar-perfil.html");
        return mav;
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
