package com.algaworks.springrest.exceptions;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<Error> errors = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			errors.add(new Error(nome, mensagem));
		}
		
		ExceptionError error = new ExceptionError();
		error.setDataHora(OffsetDateTime.now());
		error.setStatus(status.value());
		error.setTitulo("Um ou mais campos inválidos!");
		error.setErrors(errors);
		
		return handleExceptionInternal(ex, error, headers, status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionError error = new ExceptionError();
		error.setDataHora(OffsetDateTime.now());
		error.setStatus(status.value());
		error.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ExceptionError error = new ExceptionError();
		error.setDataHora(OffsetDateTime.now());
		error.setStatus(status.value());
		error.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}

}
