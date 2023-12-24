package com.github.daniellribeiro.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
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
import com.github.daniellribeiro.domain.repository.Clientes;

@Controller
@RequestMapping("/api/clientes")
public class RestControllerCliente {
	@Value("${upload.dir.clientes}")
	private String uploadDirClientes;

	private Clientes clientes;

	public RestControllerCliente(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent())
			return ResponseEntity.ok(cliente.get());

		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "nome/{nome}")
	@ResponseBody
	public ResponseEntity getClienteByNome(@PathVariable String nome) {
		List<Cliente> listaClientes = clientes.encontrarPorNome(nome);

		if (listaClientes.size() > 1) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Cliente> cliente = Optional.ofNullable(listaClientes.get(0));

		if (cliente.isPresent())
			return ResponseEntity.ok(cliente.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity salvarCliente(@RequestBody Map<String, String> requestBody) {
		String nome = requestBody.get("nome");
		String cpf = requestBody.get("cpf");
		String imagem = requestBody.get("imagem");
		
		Cliente cliente = new Cliente(nome);
		
		cliente.setCpf(cpf);
		
		cliente.setImagem(imagem);
		
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public void deletarCliente(@PathVariable Integer id) {
		clientes.deleteById(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity atualizarCliente(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
		String nome = requestBody.get("nome");
		String cpf = requestBody.get("cpf");
		String imagem = requestBody.get("imagem");
		
		Cliente cliente = new Cliente(id,nome);
		
		cliente.setCpf(cpf);
		
		cliente.setImagem(imagem);
		
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
}
