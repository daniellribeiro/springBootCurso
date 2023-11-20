package com.github.daniellribeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.repository.Clientes;
import com.github.daniellribeiro.service.Animal;
import com.github.daniellribeiro.service.Cachorro;

@SpringBootApplication
@RestController
public class VendasAplicacao {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Daniel");
			clientes.salvar(cliente);
		};
	}
	
	@Cachorro
	private Animal animal;
		
	public static void main(String[] args) {
		SpringApplication.run(VendasAplicacao.class, args);
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello World";
	}
		
	@Bean(name="executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}
}
