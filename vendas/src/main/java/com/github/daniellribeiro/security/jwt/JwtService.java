package com.github.daniellribeiro.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.github.daniellribeiro.VendasAplicacao;
import com.github.daniellribeiro.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	@Value("$(security.jwt.expiracao)")
	private String expiracao;
	@Value("$(security.jwt.chave-assinatura")
	private String chaveAssinatura;

	private String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);

		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder().setSubject(usuario.getLogin())
										.setExpiration(data)
										.signWith(SignatureAlgorithm.HS512,chaveAssinatura)
										.compact();
	}
	
	public Claims obterClaims(String token) throws ExpiredJwtException{
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJwt(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = 
					dataExpiracao.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			
			return !LocalDateTime.now().isAfter(data);
		}catch(Exception ex) {
			return false;
		}
	}	
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException{
		return (String) obterClaims (token).getSubject();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(VendasAplicacao.class);
		JwtService service = contexto.getBean(JwtService.class);
	    //Usuario usuario = Usuario.builder().login("fulano").build();
	    //String token = service.gerarToken(usuario);
	    //System.out.println(token);
		
		System.out.println("O token esta valido:");
		//System.out.println(service.obterLoginUsuario(token));
	}
}
