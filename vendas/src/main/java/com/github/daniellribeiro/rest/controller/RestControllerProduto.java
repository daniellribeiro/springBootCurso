package com.github.daniellribeiro.rest.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.entity.Produto;
import com.github.daniellribeiro.domain.repository.Produtos;

@Controller
@RequestMapping("/api/produtos")
public class RestControllerProduto {
	
	private Produtos produtos;
	
	public RestControllerProduto(Produtos produtos) {
		this.produtos = produtos;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity getProdutoById(@PathVariable Integer id) {
		Optional<Produto> produto = produtos.findById(id);
		if(produto.isPresent())
			return ResponseEntity.ok(produto.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity salvarProduto(@RequestBody String descricao,@RequestBody String preco) {
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setPreco_unitario(Double.parseDouble(preco));
		
		Produto produtoSalvo = produtos.save(produto);
		return ResponseEntity.ok(produtoSalvo);
	}
}
