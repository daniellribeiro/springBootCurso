package com.github.daniellribeiro.service;

import java.util.Optional;

import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar (PedidoDTO dto);
	
	Optional<Pedido> consultarPedido(Integer id);

	void cancelarPedido(Integer id);
}
