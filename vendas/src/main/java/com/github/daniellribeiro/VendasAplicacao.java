package com.github.daniellribeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasAplicacao {
	@Autowired
	@Qualifier("aplicacaoNome")
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
}
