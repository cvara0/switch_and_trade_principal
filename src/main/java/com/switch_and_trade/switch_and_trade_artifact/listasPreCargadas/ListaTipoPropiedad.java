package com.switch_and_trade.switch_and_trade_artifact.listasPreCargadas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@Component
public class ListaTipoPropiedad {
    private final List<String> listaTipoPropiedad=new ArrayList<>(Arrays.asList(
            "lista tipos de propiedades"
            ));
}
