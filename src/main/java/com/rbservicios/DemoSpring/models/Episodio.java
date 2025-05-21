package com.rbservicios.DemoSpring.models;

import org.springframework.cglib.core.Local;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate lanzamiento;

    public Episodio(Integer numero, DatosEpisodios d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = Integer.valueOf(d.episodio());
        try{
            this.evaluacion = Double.valueOf(d.puntaje());
        }catch (NumberFormatException ex){
            this.evaluacion = 0.0;
        }
        try{
            this.lanzamiento = LocalDate.parse(d.lanzamiento());
        }catch (DateTimeParseException ex){
            this.lanzamiento = null;
        }
    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(LocalDate lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", lanzamiento=" + lanzamiento ;
    }
}
