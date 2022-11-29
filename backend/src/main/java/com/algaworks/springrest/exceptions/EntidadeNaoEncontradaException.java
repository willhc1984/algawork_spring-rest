package com.algaworks.springrest.exceptions;

public class EntidadeNaoEncontradaException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
