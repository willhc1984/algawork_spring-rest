package com.algaworks.springrest.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionError {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Error> errors;
	
	public ExceptionError() {
	}
	
	public ExceptionError(Integer status, LocalDateTime dataHora, String titulo, List<Error> errors) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
	
}
