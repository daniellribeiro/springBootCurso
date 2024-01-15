package com.github.daniellribeiro.service;

import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar (PedidoDTO dto);
}
