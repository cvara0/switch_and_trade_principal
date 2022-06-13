package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Provincia;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.servicios.LocalidadServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/autenticaciones")
@RequiredArgsConstructor
public class AutenticacionControlador {
    private final PerfilServicio perfilServicio;
    private final LocalidadServicio localidadServicio;

    @GetMapping("/formulario-iniciar-sesion-o-registrar")
    public ModelAndView formularioIniciarSesion(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("formulario-iniciar-sesion-o-registrar.html");
        //parte de formulario iniciar sesion
        if (error != null) mav.addObject("mensajeError", "Email o clave incorrectos");
        if (logout != null) mav.addObject("mensajeSalir", "Sesion finalizada");
        if (principal != null) mav.setViewName("redirect:/");

        //parte de formulario registrar
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {//sirve para cuando se registra por primera vez
            mav.addObject("exception", inputFlashMap.get("atributoFlashExcepcion"));
            mav.addObject("user", inputFlashMap.get("atributoFlashUsuario"));
        } else {
            Perfil perfil = new Perfil();
            perfil.setRol(Rol.USUARIO);
            perfil.setEliminado(false);
            mav.addObject("todoLocalidad",localidadServicio.traerTodo());
            mav.addObject("objetoPerfil", perfil);
        }

        return mav;
    }

    //si principal es distinto de null cuando se esta logeado,
    // entonces si principal!=null me redirige a otra pag, esto es para no iniciar secion de nuevo
    @PostMapping("/registrar")
    public RedirectView registrar(Perfil dto, HttpServletRequest request, RedirectAttributes attribute) {
        RedirectView redirect = new RedirectView("/");

        try {
            perfilServicio.insertar(dto);
            request.login(dto.getEmail(), dto.getClave());
        } catch (IllegalArgumentException e) {
            attribute.addFlashAttribute("atributoFlashUsuario", dto);
            attribute.addFlashAttribute("atributoFlashExcepcion", e.getMessage());
            redirect.setUrl("/autorizaciones/formulario-iniciar-sesion");
        } catch (ServletException e) {
            attribute.addFlashAttribute("atributoFlashError", "Fallo registrarse automaticamente");
        }

        return redirect;
    }
}
