package com.github.daniellribeiro.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.daniellribeiro.domain.entity.Usuario;
import com.github.daniellribeiro.service.impl.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	private UsuarioServiceImpl usuarioService;
	private PasswordEncoder passwordEncoder;
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
//		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
//		usuario.setSenha(senhaCriptografada);
//		return usuarioService.salvar(usuario);
//	}
}
