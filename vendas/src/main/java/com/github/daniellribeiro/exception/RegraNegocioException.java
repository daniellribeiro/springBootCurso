package com.github.daniellribeiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RegraNegocioException extends RuntimeException{
	
	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}
}
