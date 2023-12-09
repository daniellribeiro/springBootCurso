package com.github.daniellribeiro.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.daniellribeiro.domain.entity.Cliente;

@Repository
public class Clientes {
	private static String SELECT = "SELECT * FROM CLIENTE";

	private static String INSERT = "INSERT INTO CLIENTE(NOME) VALUES (?)";

	private static String UPDATE = "UPDATE CLIENTE SET NOME = (?) WHERE ID = (?)";

	private static String DELETE = "DELETE FROM CLIENTE WHERE ID = (?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] { id });
	}

	public void atualizar(String nome, Integer id) {
		jdbcTemplate.update(UPDATE, new Object[] { nome, id });
	}

	public void salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
	}

	public List<Cliente> buscarTodos() {
		return jdbcTemplate.query(SELECT, obterClienteMapper());
	}

	public List<Cliente> buscarClientePorId(Integer id) {
		return jdbcTemplate.query(SELECT.concat(" WHERE id = (?)"), new Object[] { id }, obterClienteMapper());

	}

	public List<Cliente> buscarClientePorNome(String nome) {
		return jdbcTemplate.query(SELECT.concat(" WHERE nome like (?)"), new Object[] { "%" + nome + "%" },
				obterClienteMapper());
	}

	public RowMapper<Cliente> obterClienteMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
			};
		};
	}

}
