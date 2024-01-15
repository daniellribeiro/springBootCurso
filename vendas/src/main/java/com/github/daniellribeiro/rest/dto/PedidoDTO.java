package com.github.daniellribeiro.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO { 
	//DTO = Data Transfer Object
	private Integer cliente;
	private BigDecimal total;
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