package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Publicacion;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PublicacionServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.TipoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/publicaciones")
@RequiredArgsConstructor
public class PublicacionControlador {


    private final PerfilServicio perfilServicio;
    private final TipoServicio tipoServicio;
    private final ProvinciaServicio provinciaServicio;
    private final PublicacionServicio publicacionServicio;

    @GetMapping("/tabla-todo-publicacion")
    public ModelAndView tablaTodoPublicacion(HttpSession session) {//llaman al mismo html con vehiculo pero le mandan cosas diferentes
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion.html");
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("listaPublicacion", publicacionServicio.traerTodoNoEliminado());
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion-palabra-clave-tipo")
    public ModelAndView tablaTodoPublicacionPalabraClaveTipo(HttpSession session,@RequestParam(value="palabra", required = false)String palabra) {//llaman al mismo html con vehiculo pero le mandan cosas diferentes
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion.html");
        mav.addObject("idPerfil",session.getAttribute("id"));

        mav.addObject("listaPublicacion", publicacionServicio.traerTodoPorPalabraClaveTipoPublicacion(palabra));
        System.out.println(palabra);
        System.out.println(publicacionServicio.traerTodoPorPalabraClaveTipoPublicacion(palabra));

        return mav;
    }
    @GetMapping("/tabla-todo-publicacion-perfil")
    public ModelAndView tablaTodoPublicacionPerfil(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-perfil.html");

        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("listaPublicacionPerfil", publicacionServicio.traerTodoNoEliminadoPorIdPerfil((Long) session.getAttribute("id")));
        return mav;
    }

    @GetMapping("/tabla-todo-publicacion-match-oferta-perfil/{id}")
    public ModelAndView tablaTodoPublicacionMatchOfertaPerfil(HttpSession session,@PathVariable Long id){
        ModelAndView mav = new ModelAndView("tabla-todo-publicacion-match-oferta-perfil.html");

        if(!session.getAttribute("id").equals(perfilServicio.traerIdPorIdPublicacion(id)))
            return new ModelAndView("redirect:/publicaciones/tabla-todo-publicacion-perfil");
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("PublicacionPerfil", publicacionServicio.traerPorId(id));
        mav.addObject("listaPublicaionMachOfertaPerfil",publicacionServicio.traerTodoPorOfertaIdPublicacion(id));
        return mav;
    }

    @GetMapping("/formulario-insertar/{id}")
    public ModelAndView formularioInsertar(HttpSession session,@PathVariable Long id) {//@PathVariable Long id,
        ModelAndView mav = new ModelAndView("formulario-insertar-o-actualizar-publicacion.html");

        if(!session.getAttribute("id").equals(id))
            return new ModelAndView("redirect:/publicaciones/tabla-todo-publicacion");

        Perfil perfil=perfilServicio.traerPorId((Long) session.getAttribute("id"));
        Publicacion publicacion =new Publicacion();
        publicacion.setPerfil(perfil);
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("accion","insertar");
        mav.addObject("objetoPublicacion", publicacion);
        mav.addObject("listaTipo", tipoServicio.traerTodo());
        mav.addObject("listaProvincia", provinciaServicio.traerTodo());
        //mav.addObject("listaTipoDeseado",listaProvincia.getListaProvincia() /*concatenar arraylist tipo deseado tipo propiedad*/);
        return mav;
    }

    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(HttpSession session,@PathVariable Long id) {//,
        ModelAndView mav = new ModelAndView("formulario-insertar-o-actualizar-publicacion.html");

       if(!session.getAttribute("id").equals(perfilServicio.traerIdPorIdPublicacion(id)))
           return new ModelAndView("redirect:/publicaciones/tabla-todo-publicacion-perfil");

        Publicacion publicacion = publicacionServicio.traerPorId(id);
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("accion","actualizar");
        mav.addObject("objetoPublicacion", publicacion);
        mav.addObject("listaTipo", tipoServicio.traerTodo());
        mav.addObject("listaProvincia", provinciaServicio.traerTodo());
        //mav.addObject("listaTipoDeseado",listaProvincia.getListaProvincia() );/*concatenar arraylist tipo deseado tipo propiedad*/
        return mav;
    }

    @PostMapping("/insertar")
    public RedirectView insertar(Publicacion dto, @RequestParam(required = false) MultipartFile publicacionFotoName) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion-perfil");

        try {
            publicacionServicio.insertar(dto,publicacionFotoName);
        } catch (Exception e) {
            redirect.setUrl("/publicaciones/tabla-todo-publicacion-perfil");
        }
        return redirect;
    }

    @PostMapping("/actualizar")
    public RedirectView actualizar(Publicacion dto, @RequestParam(required = false) MultipartFile publicacionFotoName) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion-perfil");
        publicacionServicio.actualizar(dto,publicacionFotoName);
        return redirect;
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id,HttpSession session) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion-perfil");
        if(session.getAttribute("rol").equals("ADMINISTRADOR"))
            redirect = new RedirectView("/publicaciones/tabla-todo-publicacion");
        publicacionServicio.eliminarPorId(id);
        return redirect;
    }




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
