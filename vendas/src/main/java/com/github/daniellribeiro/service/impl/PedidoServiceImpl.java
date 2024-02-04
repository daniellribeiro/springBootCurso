package com.github.daniellribeiro.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.ItemPedido;
import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.domain.entity.Produto;
import com.github.daniellribeiro.domain.repository.Clientes;
import com.github.daniellribeiro.domain.repository.ItensPedido;
import com.github.daniellribeiro.domain.repository.Pedidos;
import com.github.daniellribeiro.domain.repository.Produtos;
import com.github.daniellribeiro.exception.RegraNegocioException;
import com.github.daniellribeiro.rest.dto.ItemPedidoDTO;
import com.github.daniellribeiro.rest.dto.PedidoDTO;
import com.github.daniellribeiro.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	private final Pedidos pedidos;
	private final Clientes clientes;
	private final Produtos produtos;
	private final ItensPedido itensPedido;
	
	public PedidoServiceImpl(Pedidos pedidos, Clientes clientes, Produtos produtos, ItensPedido itensPedido) {
		this.pedidos = pedidos;
		this.clientes = clientes;
		this.produtos = produtos;
		this.itensPedido = itensPedido;
	}

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		
		Cliente cliente = clientes.findById(dto.getCliente()).orElseThrow(() -> new RegraNegocioException("Codigo de cliente invalido"));
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemPedido = converterItens(pedido,dto.getItens());
		
		pedidos.save(pedido);
		
		itensPedido.saveAll(itemPedido);
		pedido.setItens(itemPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Nao e possivel realizar pedido sem itens");
		}else {
			return itens.stream().map(dto -> {
				Produto produto = produtos.findById(dto.getProduto())
						.orElseThrow(() -> new RegraNegocioException("Codigo do Produto Invalido" + dto.getProduto()));
				
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setQuantidade(dto.getQuantidade());
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				return itemPedido;
			}).collect(Collectors.toList());
		}
	}

	@Override
	public Optional<Pedido> consultarPedido(Integer id) {
		return pedidos.findById(id);
	}
}
