package com.switch_and_trade.switch_and_trade_artifact.servicios;

import com.switch_and_trade.switch_and_trade_artifact.entidades.Rol;
import com.switch_and_trade.switch_and_trade_artifact.entidades.Perfil;
import com.switch_and_trade.switch_and_trade_artifact.repositorios.PerfilRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class PerfilServicio implements UserDetailsService {

    private final PerfilRepositorio perfilRepositorio;
    private final BCryptPasswordEncoder encriptador;//esto se puede hacer porque tengo el bean creado
    private final EmailServicio emailServicio;
    private final FotoServicio fotoServicio;
    // inicio metodos basicos


    @Transactional
    public void insertar(Perfil dto) {
        if (perfilRepositorio.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado!");
        }
        Perfil perfil = new Perfil();
        perfil.setEmail(dto.getEmail());
        perfil.setClave(encriptador.encode(dto.getClave()));//se encripta la constrasenia
        if (perfilRepositorio.findAll().isEmpty())
            perfil.setRol(Rol.ADMINISTRADOR);
        else
            perfil.setRol(Rol.USUARIO);
        perfil.setProvincia(dto.getProvincia());
        perfil.setLocalidad(dto.getLocalidad());
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setEliminado(false);

        perfilRepositorio.save(perfil);

        //emailServicio.send(dto.getEmail());
    }

    @Transactional
    public void actualizar(Perfil dto, MultipartFile foto) {
        Perfil perfil = perfilRepositorio.findById(dto.getId()).get();
        perfil.setEmail(dto.getEmail());
        perfil.setClave(encriptador.encode(dto.getClave()));//se encripta la constrasenia
        perfil.setRol(dto.getRol());
        perfil.setProvincia(dto.getProvincia());
        perfil.setLocalidad(dto.getLocalidad());
        if (!foto.isEmpty()) perfil.setFoto(fotoServicio.copy(foto));
        perfil.setNombre(dto.getNombre());
        perfil.setApellido(dto.getApellido());
        perfil.setTelefono(dto.getTelefono());
        perfil.setEliminado(dto.getEliminado());

        perfilRepositorio.save(perfil);
    }

    @Transactional(readOnly = true)
    public Perfil traerPorId(Long id) {
        return perfilRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Perfil> traerTodo() {
        return perfilRepositorio.findAll();
    }

    @Transactional
    public void eliminarPorId(Long id) {
        perfilRepositorio.deleteById(id);
    }
    /*
    sirve para saber si alguien existe o no existe en la bd cuando me quiera logear
    pregunta a la bd si existe una instancia con el email que le estoy pasando
    * */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {//sprint security envia el parametro mail automaticamente
        Perfil perfil = perfilRepositorio.findByEmail(email)//orElseThrow es un metodo de la clase optional que permite usar una expresion lambda
                .orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));//una lamabda es (parametro)->(return)
        //dudas con esto
        GrantedAuthority authority = () -> "ROLE_" + perfil.getRol().name();//funcion que implementa los roles
        //dudas con esto
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", perfil.getId());
        session.setAttribute("email", perfil.getEmail());
        session.setAttribute("rol", perfil.getRol().name());

        return new org.springframework.security.core.userdetails.User(perfil.getEmail(), perfil.getClave(), singletonList(authority));
        //Si existe un email en la base de datos , User recibe 3 parametros, email, clave y las autorizaciones, el alcance, si puede leer o modificar
        //si se trabaja sin roles se envia una lista vacia Collections.emptyList(), en vez de singleton...
        //registra en el contexto de seguridad de spring a ese usuario, si el usuario eprtenece al contexto ingresa a la aplicaciom, sino no
    }




    //fin metodos basicos

    // inicio metodos personalizados

// fin metodos personalizados


}