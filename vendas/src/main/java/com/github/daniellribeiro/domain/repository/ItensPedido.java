package com.github.daniellribeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.daniellribeiro.domain.entity.ItemPedido;

public interface ItensPedido extends JpaRepository<ItemPedido,Integer>{

}
