package com.github.daniellribeiro.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.daniellribeiro.domain.entity.Cliente;

@Repository
public class Clientes {
	private static String INSERT = "INSERT INTO CLIENTE(NOME) VALUES (?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT,new Object[] {cliente.getNome()});
	}
}
