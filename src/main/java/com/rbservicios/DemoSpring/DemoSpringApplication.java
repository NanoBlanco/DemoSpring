package com.rbservicios.DemoSpring;

import com.rbservicios.DemoSpring.services.ConsumirApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumirApi();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?apikey=36ff2083&t=Savage");
		System.out.println(json);
	}
}
