package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	private final MovieRepository repository;

	@Autowired
	public Uri2611Application(MovieRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieMinProjection> list = repository.searchGenre("Action");
		List<MovieMinDTO> listConvert = list.stream().map(MovieMinDTO::new).collect(Collectors.toList());

		System.out.println("RESULTADO SQL RAIZ");
		for (MovieMinDTO dto: listConvert) {
			System.out.println(dto);
		}

		List<MovieMinDTO> listJPQL = repository.searchGenreJPQL("Action");

		System.out.println("RESULTADO JPQL");
		for (MovieMinDTO dto: listJPQL) {
			System.out.println(dto);
		}


	}
}
