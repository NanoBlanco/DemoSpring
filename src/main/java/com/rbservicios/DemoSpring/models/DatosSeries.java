package com.rbservicios.DemoSpring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSeries(@JsonAlias("Title") String titulo,
                          @JsonAlias("Year") String annio,
                          @JsonAlias("Genre") String genero,
                          @JsonAlias("imdbRating") String puntuacion,
                          @JsonAlias("totalSeasons") Integer totalTemporadas) {
}
