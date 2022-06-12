package com.switch_and_trade.switch_and_trade_artifact.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncriptadorConfiguracion {
    @Bean
    public BCryptPasswordEncoder encriptar(){
        return new BCryptPasswordEncoder();
    }//este metodo encripta las claves, este es el mas usado
}
