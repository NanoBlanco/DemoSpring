package com.rbservicios.DemoSpring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodios(@JsonAlias("Title") String titulo,
                             @JsonAlias("Year") String annio,
                             @JsonAlias("Released") String lanzamiento,
                             @JsonAlias("imdbRating") String puntaje) {
}
