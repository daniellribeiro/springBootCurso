package com.github.daniellribeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.daniellribeiro.model.Cliente;
import com.github.daniellribeiro.repository.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio repositorio;
		
	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.repositorio.persistir(cliente);
	}

	public void validarCliente(Cliente cliente) {
	
	}
}
