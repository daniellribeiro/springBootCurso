package com.github.daniellribeiro;

import java.util.List;

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
			clientes.salvar(new Cliente("Daniel"));
			clientes.salvar(new Cliente("Jose"));
			clientes.salvar(new Cliente("Teste"));
			clientes.salvar(new Cliente("Gabriel"));
			clientes.salvar(new Cliente("Matheus"));
		    
			Cliente clienteAtualizar = clientes.buscarClientePorId(2);
			
			clienteAtualizar.setNome("Teste2");
			
			clientes.atualizar(clienteAtualizar);
			
			
			
			clientes.deletar(clientes.buscarClientePorId(4));
			
			

			clientes.buscarClientePorNome("Matheu").forEach(System.out::println);
			
			System.out.println("TODOS:");
			clientes.buscarTodos().forEach(System.out::println);
			
			clientes.deletar(clientes.buscarClientePorId(1));
			clientes.deletar(clientes.buscarClientePorId(2));
			clientes.deletar(clientes.buscarClientePorId(3));
			clientes.deletar(clientes.buscarClientePorId(5));
			
			System.out.println(clientes.buscarTodos().isEmpty() ? "Vazio" : "Tem valor");
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
