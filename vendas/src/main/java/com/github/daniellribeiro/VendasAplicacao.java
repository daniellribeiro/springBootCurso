package com.github.daniellribeiro;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.domain.repository.Clientes;
import com.github.daniellribeiro.domain.repository.Pedidos;

@SpringBootApplication
@RestController
public class VendasAplicacao {
	private static Clientes clientes;
	private static Pedidos pedidos;
	public static void main(String[] args) {
		SpringApplication.run(VendasAplicacao.class, args);
	}
}
