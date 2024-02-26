package com.github.daniellribeiro.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.github.daniellribeiro.validation.NotEmptyList;

public class PedidoDTO { 
	//DTO = Data Transfer Object
	@NotNull(message = "Informe o codigo do cliente")
	private Integer cliente;
	
	@NotNull(message = "Campo Total do pedido e obrigatorio")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido nao pode ser realizado sem itens")
	private List<ItemPedidoDTO> itens;
	
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ItemPedidoDTO> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}
	
	
}
