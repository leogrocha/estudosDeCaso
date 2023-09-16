package com.devsuperior.uri2990;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.repositories.EmpregadoRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("SQL");
		var listEmployessNoProjects = repository.listEmployeeNoPojects().stream().map(EmpregadoDeptDTO::new).collect(Collectors.toList());

		for (EmpregadoDeptDTO list : listEmployessNoProjects) {
			System.out.println(list);
		}

		System.out.println("JPQL");
		var listEmployessNoProjectsJPQL = repository.listEmployeeNoPojectsJPQL();
		for (EmpregadoDeptDTO list : listEmployessNoProjectsJPQL) {
			System.out.println(list);
		}

		
	}
}
