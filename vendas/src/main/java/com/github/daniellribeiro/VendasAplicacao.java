package com.github.daniellribeiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.service.Animal;
import com.github.daniellribeiro.service.Cachorro;

@SpringBootApplication
@RestController
public class VendasAplicacao {
	
	@Cachorro
	private Animal animal;
	
	@Value("${aplicacao.nome}")
	private String nomeAplicacao;
	
	public static void main(String[] args) {
		SpringApplication.run(VendasAplicacao.class, args);
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello World";
	}
	
	@GetMapping("/nomeAplicacao")
	public String nomeAplicacao() {
		return nomeAplicacao;
	}
	
	@Bean(name="executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}
}
