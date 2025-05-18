package com.rbservicios.DemoSpring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPeliculas(@JsonAlias("Title") String titulo,
                             @JsonAlias("Year") String annio,
                             @JsonAlias("Released") String lanzamiento,
                             @JsonAlias("Genre") String genero,
                             @JsonAlias("imdbRating") String evaluacion) {


}
