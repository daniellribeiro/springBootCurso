package com.github.daniellribeiro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.domain.repository.Clientes;
import com.github.daniellribeiro.domain.repository.Pedidos;
import com.github.daniellribeiro.service.Animal;
import com.github.daniellribeiro.service.Cachorro;

@SpringBootApplication
@RestController
public class VendasAplicacao {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			clientes.save(new Cliente("Daniel"));
			clientes.save(new Cliente("Jose"));
			clientes.save(new Cliente("Teste"));
			clientes.save(new Cliente("Gabriel"));
			clientes.save(new Cliente("Matheus"));

			Cliente clienteAtualizar = clientes.findById(2).orElse(null);

			clienteAtualizar.setNome("Teste2");

			clientes.save(clienteAtualizar);

			clientes.deleteById(4);

			Pedido p = new Pedido();
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
			p.setCliente(clientes.findById(3).orElse(null));
			pedidos.save(p);
			
			Pedido p1 = new Pedido();
            p1.setDataPedido(LocalDate.now());
            p1.setTotal(BigDecimal.valueOf(100));
			p1.setCliente(clientes.findById(3).orElse(null));
			pedidos.save(p1);

			System.out.println("PEDIDOS: ");
			Cliente c = clientes.todosOsPedidosDoCliente(3);
			System.out.println(c.getPedidos());
			
			pedidos.findByCliente(c).forEach(System.out::println);
			
			clientes.encontrarPorNome("%Matheu%").forEach(System.out::println);

			System.out.println("TODOS:");
			clientes.findAll().forEach(System.out::println);

			clientes.deleteById(1);
			clientes.deleteById(2);
			//clientes.deleteById(3);
			clientes.deleteById(5);

			System.out.println(clientes.findAll().isEmpty() ? "Vazio" : "Tem valor");
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

	@Bean(name = "executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}
}
