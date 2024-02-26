package com.github.daniellribeiro.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.daniellribeiro.domain.entity.Cliente;
import com.github.daniellribeiro.domain.repository.Clientes;

@RestController
@Validated
@RequestMapping("/api/clientes/")
public class RestControllerCliente {
	private Clientes clientes;

	public RestControllerCliente(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping(value = "{id}")
	public Cliente getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent())
			return cliente.get();

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado");
	}

	@GetMapping(value = "nome/{nome}")
	public Cliente getClienteByNome(@PathVariable String nome) {
		List<Cliente> listaClientes = clientes.encontrarPorNome(nome);

		if (listaClientes.size() > 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Realizar a consulta pelo id");
		}

		Optional<Cliente> cliente = Optional.ofNullable(listaClientes.get(0));

		if (cliente.isPresent())
			return cliente.get();

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado");
	}

	@PostMapping
	public ResponseEntity salvarCliente(@Valid @RequestBody Map<String, String> requestBody) {

		String nome = requestBody.get("nome");
		String cpf = requestBody.get("cpf");
		String imagem = requestBody.get("imagem");

		Cliente cliente = new Cliente(nome);

		cliente.setCpf(cpf);

		cliente.setImagem(imagem);

		try {
			Cliente clienteSalvo = clientes.save(cliente);
			return ResponseEntity.ok(clienteSalvo);
		} catch (Exception ex) {
			String mensagem = validarMensagem(nome, cpf);
			return ResponseEntity.badRequest().body(mensagem);
		}

	}

	@DeleteMapping(value = "{id}")
	public void deletarCliente(@PathVariable Integer id) {
		clientes.deleteById(id);
	}

	@PutMapping(value = "{id}")
	public Cliente atualizarCliente(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
		String nome = requestBody.get("nome");
		String cpf = requestBody.get("cpf");
		String imagem = requestBody.get("imagem");

		Cliente cliente = new Cliente(id, nome);

		cliente.setCpf(cpf);

		cliente.setImagem(imagem);

		Cliente clienteSalvo = clientes.save(cliente);
		return clienteSalvo;
	}

	private String validarMensagem(String nome, String cpf) {
		String mensagem = " Erro: Campos Invalidos";

		if (nome.isEmpty()) {
			mensagem = mensagem + " Erro: Nome nao pode ser vazio";
		}

		if (cpf.isEmpty()) {
			mensagem = mensagem + " Erro: Cpf nao pode ser vazio";
		}

		return mensagem;
	}
}
