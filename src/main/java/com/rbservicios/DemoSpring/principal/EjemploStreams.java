package com.rbservicios.DemoSpring.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Rey", "Jose","Pedro", "Gaby", "Luisa","Roberto");
        nombres.stream()
                .sorted()
                .filter(n->n.startsWith("R"))
                .map(n->n.toUpperCase())
                .forEach(System.out::println);
    }
}
