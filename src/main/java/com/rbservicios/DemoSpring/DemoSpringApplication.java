package com.rbservicios.DemoSpring;

import com.rbservicios.DemoSpring.models.DatosPeliculas;
import com.rbservicios.DemoSpring.models.DatosEpisodios;
import com.rbservicios.DemoSpring.models.DatosSeries;
import com.rbservicios.DemoSpring.models.DatosTemporadas;
import com.rbservicios.DemoSpring.services.ConsumirApi;
import com.rbservicios.DemoSpring.services.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumirApi();
		var convertir = new ConvertirDatos();
		// Peliculas
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?apikey=36ff2083&t=Savage");
		System.out.println(json);
		var datosPeliculas = convertir.obtenerDatos(json, DatosPeliculas.class);
		System.out.println(datosPeliculas);
		// Series
		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?apikey=36ff2083&t=Prison+break");
		System.out.println(json);
		var datosSeries = convertir.obtenerDatos(json, DatosSeries.class);
		System.out.println(datosSeries);
		// Episodios
		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?apikey=36ff2083&t=Prison+break&Season=2&episode=1");
		System.out.println(json);
		var datosEpisodios = convertir.obtenerDatos(json, DatosEpisodios.class);
		System.out.println(datosEpisodios);

		// Temporadas
		List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <=datosSeries.totalTemporadas(); i++) {
			json = consumoApi.obtenerDatos("https://www.omdbapi.com/?apikey=36ff2083&t=Prison+break&Season="+i);
			var datosTemporadas = convertir.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}
}
