package com.switch_and_trade.switch_and_trade_artifact.controladores;

import com.switch_and_trade.switch_and_trade_artifact.servicios.LocalidadServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.ProvinciaServicio;
import com.switch_and_trade.switch_and_trade_artifact.servicios.PerfilServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilControlador {
    private final PerfilServicio perfilServicio;
    private final ProvinciaServicio provinciaServicio;
    private final LocalidadServicio localidadServicio;
   /* @GetMapping("/formulario-registrarse")
    public ModelAndView formularioRegistrarse(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("formulario-registrarse");
        //mav.addObject("todoProvincia",provinciaServicio.traerTodo());
        //mav.addObject("todoLocalidad",localidadServicio.traerTodo());
        mav.addObject("objetoNuevoUsuarioDtoVacio",new NuevoUsuarioDto());

        return mav;
    }

    @GetMapping("/formulario-iniciar-sesion")
    public ModelAndView formularioIniciarSesion(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("formulario-iniciar-sesion");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) mav.setViewName("redirect:/");

        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("usuario", inputFlashMap.get("usuario"));
        } else {
            Perfil perfil = new Perfil();
            perfil.setRol(Rol.USUARIO);
            mav.addObject("usuario", perfil);
        }

        return mav;
    }
    @PostMapping("/registrarse")
    public RedirectView signup(NuevoUsuarioDto dto, HttpServletRequest request, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");
        Localidad localidadSeleccionada=new Localidad();
        Provincia provinciaSeleccionada=new Provincia();
        Perfil nuevoUsuario=new Perfil();
        Perfil nuevoPerfil=new Perfil();

        provinciaSeleccionada.setNombre(dto.getNombreProvincia());
        provinciaSeleccionada.setEliminado(false);

        localidadSeleccionada.setNombre(dto.getNombreLocalidad());
        localidadSeleccionada.setEliminado(false);
        localidadSeleccionada.setProvincia(provinciaSeleccionada);

        nuevoPerfil.setNombre(dto.getNombre());
        nuevoPerfil.setApellido(dto.getApellido());
        nuevoPerfil.setLocalidad(localidadSeleccionada);
        nuevoPerfil.setTelefono(dto.getTelefono());
        //perfil no tiene eliminado, ver eso
        perfilServicio.insertar(nuevoPerfil);

        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setClave(dto.getClave());
        //nuevoUsuario.setRol();
        nuevoUsuario.setEliminado(false);


        try {
            perfilServicio.insertar(nuevoUsuario);//ya incluye los datos del perfil
            request.login(dto.getEmail(), dto.getClave());
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("usuario", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth/sign-up");
        } catch (ServletException e) {
            attributes.addFlashAttribute("error", "Auto login failed");
        }
        return redirect;
    }*/
}