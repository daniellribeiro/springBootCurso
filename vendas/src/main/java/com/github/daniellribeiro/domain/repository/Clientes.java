package com.github.daniellribeiro.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.daniellribeiro.domain.entity.Cliente;

@Repository
public class Clientes {
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	
	private final String SELECT_NOME = "select c from Cliente c where c.nome like :nome";

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void deletar(Cliente cliente) {
		if(!entityManager.contains(cliente))
			cliente = entityManager.merge(cliente);
		
		entityManager.remove(cliente);
	}

	@Transactional
	public void atualizar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	@Transactional
	public void salvar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Cliente buscarClientePorId(Integer id) {
		return entityManager.find(Cliente.class, id);

	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarClientePorNome(String nome) {
		TypedQuery<Cliente> query = entityManager.createQuery(SELECT_NOME, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
}
