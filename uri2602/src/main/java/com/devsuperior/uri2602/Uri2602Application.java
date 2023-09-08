package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list =
				repository.searchByNameNative("rs");
		List<CustomerMinDTO> listaDTONativa =
				list.stream().map(CustomerMinDTO::new).collect(Collectors.toList());

		System.out.println("\n - Consulta nativa");
		for (CustomerMinDTO states : listaDTONativa) {
			System.out.println(states.getName());
		}
		System.out.println("\n - Consulta jpql");

		List<CustomerMinDTO> listaDTOJpql =  repository.searchByNameJpql("rs");
		for (CustomerMinDTO states : listaDTOJpql) {
			System.out.println(states.getName());
		}


	}
}
