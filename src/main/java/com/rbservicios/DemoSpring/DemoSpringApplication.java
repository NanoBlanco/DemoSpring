package com.rbservicios.DemoSpring;

import com.rbservicios.DemoSpring.models.DatosPeliculas;
import com.rbservicios.DemoSpring.models.DatosEpisodios;
import com.rbservicios.DemoSpring.models.DatosSeries;
import com.rbservicios.DemoSpring.models.DatosTemporadas;
import com.rbservicios.DemoSpring.principal.Principal;
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
		Principal principal = new Principal();
		principal.mostrarMenu();

	}
}
