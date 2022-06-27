package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
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
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilControlador {
    private final PerfilServicio perfilServicio;
    private final ProvinciaServicio provinciaServicio;
    private final PublicacionServicio publicacionServicio;
private ProvinciaControlador provinciaControlador;
private TipoControlador tipoControlador;
    private Boolean eliminadoPorPerfil=false;

//como acceder al id de perfil una vez logueado
    @GetMapping("/formulario-iniciar-sesion-o-insertar")
    public ModelAndView formularioIniciarSesionOInsertar(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal, HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("formulario-iniciar-sesion-o-insertar-perfil.html");
        //parte de formulario iniciar sesion
        //if (error != null) mav.addObject("mensajeError", "Email o clave incorrectos");
        //if (logout != null) mav.addObject("mensajeSalir", "Sesion finalizada");
        if (principal != null) mav.setViewName("redirect:/");

        //parte de formulario registrar
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

       // if (inputFlashMap != null) {//sirve para cuando se registra por primera vez
        //    mav.addObject("exception", inputFlashMap.get("atributoFlashExcepcion"));
         //   mav.addObject("user", inputFlashMap.get("atributoFlashUsuario"));
        //} else {

        mav.addObject("objetoPerfil", new Perfil());
      //  }

        return mav;
    }

    //si principal es distinto de null cuando se esta logueado,
    // entonces si principal!=null me redirige a otra pag, esto es para no iniciar sesion de nuevo
    @PostMapping("/insertar")
    public RedirectView insertar(Perfil dto, HttpServletRequest request, RedirectAttributes attribute) {
        RedirectView redirect = new RedirectView("/publicaciones/tabla-todo-publicacion");
        //si principal es distinto de null la sesion esta iniciada,
        //principal o http sesion para que no vuelva al index
        try {
            perfilServicio.insertar(dto);
            request.login(dto.getEmail(), dto.getClave());//autologin
        } catch (IllegalArgumentException e) {
            attribute.addFlashAttribute("atributoFlashUsuario", dto);
            attribute.addFlashAttribute("atributoFlashExcepcion", e.getMessage());
            redirect.setUrl("/publicaciones/tabla-todo-publicacion");
        } catch (ServletException e) {
            attribute.addFlashAttribute("atributoFlashError", "Fallo registrarse automaticamente");
        }
        return redirect;
    }

    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id,HttpSession session) {//@PathVariable Long id
        ModelAndView mav = new ModelAndView("formulario-actualizar-perfil.html");
//TODO el id del perfil se conserva en toda la sesion, hacerlo para cada metodo
        if(!session.getAttribute("id").equals(id))
            return new ModelAndView("redirect:/");
        Perfil perfil=perfilServicio.traerPorId(id);
        mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("objetoPerfil", perfil);
        //mav.addObject("listaProvincia", listaProvincia.getListaProvincia());
        return mav;
    }

    @GetMapping("/tabla-perfil")
    public ModelAndView tablaPerfil(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-perfil.html");
        mav.addObject("idPerfil",session.getAttribute("id"));
        Perfil perfil=perfilServicio.traerPorId((Long)session.getAttribute("id"));
        //mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("objetoPerfil", perfil);
        return mav;
    }
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/tabla-administrar-perfil")
    public ModelAndView tablaAdministrarPerfil(HttpSession session) {
        ModelAndView mav = new ModelAndView("tabla-administrar-perfil.html");
        mav.addObject("idPerfil",session.getAttribute("id"));
        //Perfil perfil=perfilServicio.traerPorId((Long)session.getAttribute("id"));
        //mav.addObject("idPerfil",session.getAttribute("id"));
        mav.addObject("listaTodoPerfil", perfilServicio.traerTodo());
        return mav;
    }



   @PostMapping("/actualizar")
    public RedirectView actualizarPerfil(Perfil dto, @RequestParam(required = false) MultipartFile fotoPerfilName) {
        RedirectView redirect = new RedirectView("/perfiles/tabla-perfil");
        perfilServicio.actualizar(dto,fotoPerfilName);
        return redirect;
    }



    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/perfiles/tabla-administrar-perfil");
        perfilServicio.eliminarPorId(id);
        publicacionServicio.eliminarTodoPorIdPerfil(id);
        eliminadoPorPerfil=true;
        return redirect;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/restablecer/{id}")
    public RedirectView restablecer(@PathVariable Long id) {
        perfilServicio.restablecerPorId(id);
        publicacionServicio.restablecerTodoPorIdPerfil(id);

        return new RedirectView("/perfiles/tabla-administrar-perfil");
    }



}

/*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-insertar")
    public ModelAndView formularioInsertar() {
        ModelAndView mav = new ModelAndView("formulario-insertar-perfil");
        mav.addObject("objetoPerfilVacio", new Provincia());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/formulario-actualizar/{id}")
    public ModelAndView formularioActualizar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("formulario-actualizar-perfil");//continuar aca
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
    }*/
