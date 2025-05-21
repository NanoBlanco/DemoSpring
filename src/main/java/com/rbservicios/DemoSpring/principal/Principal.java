package com.rbservicios.DemoSpring.principal;

import com.rbservicios.DemoSpring.models.*;
import com.rbservicios.DemoSpring.services.ConsumirApi;
import com.rbservicios.DemoSpring.services.ConvertirDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private final String URL_KEY = "https://www.omdbapi.com/?apikey=36ff2083";
    private ConsumirApi consumirApi = new ConsumirApi();
    private ConvertirDatos convertir = new ConvertirDatos();
    public void mostrarMenu() {
        System.out.println("Escriba lo que desea buscar : (1 = Pelicula / 2 = Serie)");
        var opcion = entrada.nextInt();
        entrada.nextLine();
        if (opcion == 1){
            System.out.println("Escriba el nombre de la pelicula");
            var pelicula = entrada.nextLine();
            var json = consumirApi.obtenerDatos(URL_KEY+"&t="+pelicula);
            var datosPeliculas = convertir.obtenerDatos(json, DatosPeliculas.class);
            System.out.println(datosPeliculas);
        }else{
            System.out.println("Escriba el nombre de la serie");
            var serie = entrada.nextLine();
            var json = consumirApi.obtenerDatos(URL_KEY+"&t="+serie.replace(" ","+"));
            var datosSeries = convertir.obtenerDatos(json, DatosSeries.class);
            System.out.println(datosSeries);

            // Temporadas
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <=datosSeries.totalTemporadas(); i++) {
                 json = consumirApi.obtenerDatos(URL_KEY+"&t="+serie.replace(" ","+")+"&Season="+i);
                 var datosTemporadas = convertir.obtenerDatos(json, DatosTemporadas.class);
                 temporadas.add(datosTemporadas);
            }

            //temporadas.forEach(System.out::println);
            /*
            for (int i = 0; i < datosSeries.totalTemporadas(); i++) {
                List<DatosEpisodios> episodios = temporadas.get(i).episodios();
                for (int j = 0; j < episodios.size(); j++) {
                    System.out.println("Titulo : "+episodios.get(j).titulo());
                }
            }
            */
            // Utilizando lambdas
            //temporadas.forEach(t-> t.episodios().forEach(e-> System.out.println("Titulo: "+e.titulo())));

            // Convertir las entradas en listas de datosEpisodios
            List<DatosEpisodios> datosEpisodios = temporadas.stream()
                    .flatMap(t->t.episodios().stream()).collect(Collectors.toList());

            /*
            System.out.println("Top 5");
            datosEpisodios.stream()
                    .filter(e->!e.puntaje().equalsIgnoreCase("N/A"))
                    .sorted(Comparator.comparing(DatosEpisodios::puntaje).reversed())
                    .limit(5)
                    .forEach(System.out::println);
            */
            // Convertir datos a clase episodios
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(t->t.episodios().stream()
                            .map(d-> new Episodio(t.numero(), d)))
                            .collect(Collectors.toList());

            //episodios.forEach(System.out::println);

            // Busqueda por año
            /*
            System.out.println("Ingrese el año a partir");
            var fecha = entrada.nextInt();
            entrada.nextLine();

            LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            episodios.stream()
                    .filter(e->e.getLanzamiento() != null && e.getLanzamiento().isAfter(fechaBusqueda))
                    .forEach(e-> System.out.println(
                            "Temporada "+e.getTemporada() +
                                    " - Episodio " + e.getTitulo() +
                                    " - Fecha de Lanzamiento " + e.getLanzamiento().format(formateador)
                    ));
            */
            /*
            // Busqueda de episodio por nombre
            System.out.println("Escriba el nombre del episodio ");
            var nombre = entrada.nextLine();

            Optional<Episodio> encontrado = episodios.stream()
                    .filter(e -> e.getTitulo().toLowerCase().contains(nombre.toLowerCase()))
                    .findFirst();

            if(encontrado.isPresent()) System.out.println(encontrado.get());
            if(!encontrado.isPresent()) System.out.println("Episodio no encontrado");
            */

            // Evaluacion de temporada
            Map<Integer, Double> evaluacionTemporada = episodios.stream()
                    .filter(e -> e.getEvaluacion() > 0.0)
                    .collect(Collectors.groupingBy(Episodio::getTemporada,
                            Collectors.averagingDouble(Episodio::getEvaluacion)));

            System.out.println(evaluacionTemporada);

            DoubleSummaryStatistics est = episodios.stream()
                    .filter(e-> e.getEvaluacion() > 0.0)
                    .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));

            System.out.println("Promedio " + est.getAverage());
            System.out.println("Episodio mejor evaluado " + est.getMax());
        }

    }
}
