package com.github.daniellribeiro.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "s_item_pedido", sequenceName = "s_item_pedido", allocationSize = 1)
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_item_pedido")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="PEDIDO")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "PRODUTO")
	private Produto produto;
	
	private Integer quantidade;

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}
}
