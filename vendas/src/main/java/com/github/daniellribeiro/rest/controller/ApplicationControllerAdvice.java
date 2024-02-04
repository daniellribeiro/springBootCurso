package com.github.daniellribeiro.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.daniellribeiro.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> lidarRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
