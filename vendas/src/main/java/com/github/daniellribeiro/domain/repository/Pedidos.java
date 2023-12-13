package com.github.daniellribeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente c);
}
