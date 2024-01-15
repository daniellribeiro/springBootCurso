package com.github.daniellribeiro.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.rest.dto.PedidoDTO;
import com.github.daniellribeiro.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class RestControllerPedido {
	
	private PedidoService pedidos;
	
	public RestControllerPedido(PedidoService pedidos) {
		this.pedidos = pedidos;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = pedidos.salvar(dto);
		return pedido.getId();
	}
}
