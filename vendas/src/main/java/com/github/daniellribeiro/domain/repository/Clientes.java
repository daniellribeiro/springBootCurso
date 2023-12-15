package com.github.daniellribeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Pedido;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer /*Id*/>{

	//List<Cliente> findByNomeLike(String nome);
	
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Query(value="SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos p where c.id = :id")
	Cliente todosOsPedidosDoCliente(@Param("id") Integer id);

	
}
