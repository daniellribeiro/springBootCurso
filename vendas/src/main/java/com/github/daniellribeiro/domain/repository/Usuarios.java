package com.github.daniellribeiro.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.daniellribeiro.domain.entity.Usuario;

public interface Usuarios extends JpaRepository<Usuario,Integer>{
	Optional<Usuario> findByLogin(String login);
}
