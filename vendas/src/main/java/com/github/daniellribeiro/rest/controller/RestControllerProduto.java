package com.github.daniellribeiro.rest.controller;

import java.math.BigDecimal;
import java.util.Map;
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
	public ResponseEntity salvarProduto(@RequestBody Map<String, String> requestBody) {
		String descricao = requestBody.get("descricao");
		String preco = requestBody.get("preco");
		
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		
		BigDecimal precoBigDecimal = new BigDecimal(preco);
        BigDecimal precoAredondado = precoBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        produto.setPreco(precoAredondado);
		
		Produto produtoSalvo = produtos.save(produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity atualizarProduto(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
		String descricao = requestBody.get("descricao");
		String preco = requestBody.get("preco");
		
		Produto produto = new Produto();
		produto.setId(id);
		produto.setDescricao(descricao);
		
		BigDecimal precoBigDecimal = new BigDecimal(preco);
        BigDecimal precoAredondado = precoBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        produto.setPreco(precoAredondado);
        
        produto.setImagem(produtos.findById(id).get().getImagem());
        
		Produto produtoSalvo = produtos.save(produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public void deletarProduto(@PathVariable Integer id) {
		produtos.deleteById(id);
	}
	
	@PutMapping(value = "/imagem/{id}")
	@ResponseBody
	public ResponseEntity atualizarImagem(@PathVariable Integer id, @RequestBody String imagem) {
		Produto produto = produtos.findById(id).orElse(null);

		produto.setImagem(imagem);

		Produto produtoSalvo = produtos.save(produto);

		return ResponseEntity.ok(produtoSalvo);
	}
}
