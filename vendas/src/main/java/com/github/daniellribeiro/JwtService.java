package com.github.daniellribeiro;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.daniellribeiro.domain.entity.Usuario;

@Service
public class JwtService {
	@Value("$(security.jwt.expiracao)")
	private String expiracao;
	@Value("$(security.jwt.chave-assinatura")
	private String chaveAssinatura;
	
	private String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		
		Date data = 
	}
	
}
