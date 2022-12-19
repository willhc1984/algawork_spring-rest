package com.algaworks.springrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.springrest.exceptions.BusinessException;
import com.algaworks.springrest.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.repositories.EntregaRepository;

@Service
public class BuscaEntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
	}

}
