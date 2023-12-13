package com.github.daniellribeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.daniellribeiro.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto,Integer>{

}
