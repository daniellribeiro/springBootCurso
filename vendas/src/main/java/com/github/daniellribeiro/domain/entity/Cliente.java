package com.github.daniellribeiro.domain.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@SequenceGenerator(name = "s_cliente", sequenceName = "s_cliente", allocationSize = 1)
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_cliente")
	private Integer id;
	
	@NotEmpty(message = "Campo nome e obrigatorio")
	private String nome;
	
	private String imagem;
	
	@NotEmpty(message = "Campo cpf e obrigatorio")
	@CPF(message = "Informe um cpf valido")
	private String cpf;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos;
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Cliente() {
		
	}
	
	@Override
	public String toString() {
		return "Cliente{" +
			   "id=" + id +
			   ", nome='" + nome + '\'' +
			   '}';
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
