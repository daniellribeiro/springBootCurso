package com.github.daniellribeiro.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.daniellribeiro.domain.entity.Usuario;
import com.github.daniellribeiro.domain.repository.Usuarios;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarios.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = new Usuario();
		
		if(!username.equals("Daniel")) {
			usuario.setLogin("Daniel");
			usuario.setSenha("123");
		}else {
			 usuario = usuarios.findByLogin(username)
			.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado na base de dados"));
		}
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles("USER")
				.build();
	}
}