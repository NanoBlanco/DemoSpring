package com.rbservicios.DemoSpring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodios(@JsonAlias("Title") String titulo,
                             @JsonAlias("Released") String lanzamiento,
                             @JsonAlias("Episode") String episodio,
                             @JsonAlias("imdbRating") String puntaje) {
}
