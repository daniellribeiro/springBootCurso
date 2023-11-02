package com.github.daniellribeiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {
	@Bean(name="aplicacaoNome")
	public String nomeAplicacao() {
		return "sistema Vendas";
	}
}
