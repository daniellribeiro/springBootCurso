package com.github.daniellribeiro.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.daniellribeiro.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<Object> lidarRegraNegocioException(RegraNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex) {
//		List<String> erros = ex.getBindingResult()
//				.getAllErrors()
//				.stream()
//				.map(erro -> erro.getDefaultMessage())
//				.collect(Collectors.toList());
//		
//		return new ApiErros(erros);
//	}

}
