package com.github.daniellribeiro.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.daniellribeiro.domain.entity.ItemPedido;
import com.github.daniellribeiro.domain.entity.Pedido;
import com.github.daniellribeiro.rest.dto.ConsultaItemPedidoDTO;
import com.github.daniellribeiro.rest.dto.ConsultaPedidoDTO;
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

	@GetMapping(value = "/{id}")
	public ConsultaPedidoDTO getById(@PathVariable Integer id) {
		return pedidos.consultarPedido(id)
				.map(p -> converter(p))
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao encontrado"));
	}

	private ConsultaPedidoDTO converter(Pedido pedido) {
		ConsultaPedidoDTO retorno = new ConsultaPedidoDTO();
		
		retorno.setCodigo(pedido.getId());
		retorno.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		retorno.setCpf(pedido.getCliente().getCpf());
		retorno.setNomeCliente(pedido.getCliente().getNome());
		retorno.setTotal(pedido.getTotal());
		retorno.setStatus(pedido.getStatus().name());
		retorno.setItens(converterItens(pedido.getItens()));
		
		return retorno;
	}
	
	private List<ConsultaItemPedidoDTO> converterItens(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		List<ConsultaItemPedidoDTO> retorno = new ArrayList<>();
		
		for(int i=0;i < itens.size();i++) {
			ConsultaItemPedidoDTO e = new ConsultaItemPedidoDTO();
			e.setDescricaoProduto(itens.get(i).getProduto().getDescricao());
			e.setPreco(itens.get(i).getProduto().getPreco());
			e.setQuantidade(itens.get(i).getQuantidade());
			
			retorno.add(e);
		}
		
		return retorno;
	}
	
	@PatchMapping("/cancelar/{id}")
	public ConsultaPedidoDTO cancelarPedido(@PathVariable("id") Integer id) {
		pedidos.cancelarPedido(id);
		
		return getById(id);
	}
}
