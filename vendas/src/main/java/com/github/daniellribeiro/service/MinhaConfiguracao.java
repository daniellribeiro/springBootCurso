package com.github.daniellribeiro.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MinhaConfiguracao {
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("RODANDO A CONFIGURACAO DE DESENVOLVIMENTO");
		};
	}
}
