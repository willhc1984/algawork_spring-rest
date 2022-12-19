package com.algaworks.springrest.model.DTO;

import javax.validation.constraints.NotBlank;

public class OcorrenciaPostDTO {
	
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
